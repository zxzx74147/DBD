package com.zxzx74147.modules.auction;

import com.zxzx74147.Main;
import com.zxzx74147.devlib.http.ZXHttpCallback;
import com.zxzx74147.devlib.http.ZXHttpRequest;
import com.zxzx74147.devlib.http.ZXHttpResponse;
import com.zxzx74147.devlib.log.ZXLog;
import com.zxzx74147.modules.Config;
import com.zxzx74147.modules.auction.data.AuctionData;
import com.zxzx74147.modules.info.Path;
import com.zxzx74147.modules.info.Status;
import com.zxzx74147.modules.info.data.BidListData;
import com.zxzx74147.modules.utils.*;
import com.zxzx74147.utils.ZXJsonUtil;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zhengxin on 2016/12/20.
 */
public class AuctionTask {
    private static final String TAG = AuctionTask.class.getName();
    private static final int ADD = 5;
    private int mPaimaiId;
    private int mPrice;
    private int mCurrentPrice = 0;
    private long mEndTime = 0;
    private Timer mTimer = new Timer();


    public AuctionTask(int paimaiId, int price) {
        mPaimaiId = paimaiId;
        mPrice = price;
        doGetInfo();
        mTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                doFresh(false);
            }
        },0);
    }

    private void doGetInfo() {
        final ZXHttpRequest<String> request = new ZXHttpRequest<>(String.class);
        request.setUrl(Config.HOST_HTML + mPaimaiId);
        HttpUtils.addHeadersHTML(request);
        request.sendSync();
        final long startTime = System.currentTimeMillis();
        ZXHttpResponse<String> response= request.sendSync();
        long nowTime = System.currentTimeMillis();

        int ping = (int) (nowTime-startTime);
        ZXLog.i(TAG,"ping time="+ping);
        java.util.regex.Matcher m = RegexUtils.matchString(response.mData, RegexTable.REGEX_START);
        ZXLog.i("date", "date=" + m.group(1));
        String dateString = m.group(1);
        mEndTime = TimeUtils.getTimestamp(dateString);
        long now = System.currentTimeMillis();
        long diff = mEndTime-now;
        ZXLog.i("距离开拍", TimeUtils.getTimeDuration(diff));
        mTimer.schedule(mAuctionTask,diff-ping*2-400);
//        request.send(new ZXHttpCallback<String>() {
//            @Override
//            public void onResponse(ZXHttpResponse<String> response) {
//
////                mTimer.schedule(mAuctionTask,10);
//
//            }
//        });
    }

    private TimerTask mAuctionTask = new TimerTask() {

        @Override
        public void run() {
            doFresh(true);

        }
    };
    int actionTime = 0;
    private void doAuction(final int price) {
        final ZXHttpRequest<AuctionData> request = new ZXHttpRequest<>(AuctionData.class);
        request.setUrl(Config.HOST_AUCTION + Path.ADDRESS_AUCTION);
        request.addParam("paimaiId", mPaimaiId);
        request.addParam("price", price);
        request.addParam("t", "759781");
        request.addParam("proxyFlag", 0);
        request.addParam("bidSource", 0);
        request.addHeader("Referer","https://dbditem.jd.com/"+mPaimaiId);
        HttpUtils.addHeaders(request);
        HttpUtils.addUserHeaders(request);
        actionTime++;
        request.send(new ZXHttpCallback<AuctionData>() {
            @Override
            public void onResponse(ZXHttpResponse<AuctionData> response) {
                ZXLog.i(TAG, "auction="+response.mData.getMessage());
                if(actionTime>1){
                    finish();
                    return;
                }
                if(response.mData.getMessage().contains("成功")){
                    finish();
                }else{
                    int aucPrice =getPrice(price,mPrice);
                    doAuction(aucPrice);
                }

            }
        });
    }

    private void doFresh(final boolean doAuction){
        final ZXHttpRequest<BidListData> request = new ZXHttpRequest<>(BidListData.class);
        request.setUrl(Config.HOST + Path.ADDRESS_INFO);
        request.addParam("paimaiId", mPaimaiId);
        request.addParam("skuId", "0");
        request.addParam("t", "759781");
        request.addParam("start", "0");
        request.addParam("end", "2");
        request.addParam("callback", "jQuery2986246");
        request.addParam("_", System.currentTimeMillis());
        HttpUtils.addHeaders(request);
        request.send(new ZXHttpCallback<BidListData>() {
            @Override
            public void onResponse(ZXHttpResponse<BidListData> response) {
                ZXLog.i("RSP", ZXJsonUtil.toJsonString(response.mData));
                if(response.mData.getAuctionStatus()== Status.STATUS_OVER){
                    ZXLog.i("RSP", "已结束");
                    finish();
                    return;
                }
                if(response.mData.getBidList().size()>0) {
                    int price = response.mData.getBidList().get(0).getPrice();
                    mCurrentPrice = Math.max(price, mCurrentPrice);
                    ZXLog.i(TAG, "current price=" + mCurrentPrice);
                    if (mCurrentPrice > mPrice) {
                        ZXLog.i(TAG, "fail!");
                        finish();
                        return;
                    }
                }
                if(doAuction){
                    int price = getPrice(mCurrentPrice,mPrice);
                    ZXLog.i(TAG, "auction price=" + price);
                    doAuction(price);
                }else {
                    long now = System.currentTimeMillis();
                    long diff = mEndTime-now;
                    long delay = 5000;
                    if(diff>10000){
                        delay = diff/2;
                    }
                    mTimer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            doFresh(false);
                        }
                    } , delay);
                }
            }
        });
    }


    private void finish(){
        synchronized (Main.mWaitObj) {
            Main.mWaitObj.notify();
        }
        mTimer.cancel();
    }

    private static int getPrice(int currentPrice,int maxPrice){
        int add = (int) (Math.log(currentPrice)*Math.sqrt(currentPrice)/5);
        add=Math.max(1,add);
        add=Math.min(35,add);
        int aucPrice = Math.min(maxPrice,currentPrice+add);
        return aucPrice;
    }
}
