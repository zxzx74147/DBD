package com.zxzx74147.modules_qiushi.info;

import com.zxzx74147.devlib.http.ZXHttpRequest;
import com.zxzx74147.devlib.http.ZXHttpResponse;
import com.zxzx74147.devlib.log.ZXLog;
import com.zxzx74147.devlib.task.BaseExecutor;
import com.zxzx74147.modules_qiushi.http.HttpUtils;
import com.zxzx74147.modules_qiushi.http.QiushiConfig;
import com.zxzx74147.modules_qiushi.info.data.ItemData;
import com.zxzx74147.modules_qiushi.info.data.ItemRspData;
import com.zxzx74147.utils.ZXJsonUtil;
import org.ansj.domain.Result;
import org.ansj.splitWord.analysis.ToAnalysis;

import javax.naming.spi.DirStateFactory;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;

/**
 * Created by zhengxin on 2016/12/21.
 */
public class GetInfoExecutor extends BaseExecutor<Integer, ItemData> {
    private static final String TAG = GetInfoExecutor.class.getName();
    private static AtomicInteger mLastCount = new AtomicInteger();
    private static AtomicLong mLastTime = new AtomicLong();

    public GetInfoExecutor(int threadNum, int bufferNum) {
        super(threadNum, bufferNum, "GetInfoExecutor Thread");
        BLOCK_NUM = 1;
    }

    @Override
    public List<ItemData> process(List<Integer> input) {
        List<ItemData> result = new LinkedList<>();
        for(Integer id:input) {
            try {
//                ZXHttpRequest<ItemData> request = new ZXHttpRequest<>(ItemData.class);
//                request.setUrl(QiushiConfig.HOST + Path.ADDRESS_ITEM+id);
//                HttpUtils.addHeaders(request);
//                ZXHttpResponse<ItemData> response = request.sendSync();
//                if (response.isSuccess()) {
//                    ZXLog.i(TAG, ZXJsonUtil.toJsonString(response.mData));
//                    result.add(response.mData);
//                }
                long time = System.currentTimeMillis();
                ZXHttpRequest<ItemRspData> request = new ZXHttpRequest<>(ItemRspData.class);
                request.setUrl(QiushiConfig.HOST + Path.ADDRESS_ITEM+id);
                HttpUtils.addHeaders(request);
                ZXHttpResponse<ItemRspData> response = request.sendSync();
                if (response.isSuccess()) {
//                    if(response.mData.article!=null) {
//                        Result temp = ToAnalysis.parse(response.mData.article.content);
//                        ZXLog.i(TAG, temp.toString());
//                    }
                    if(response.mData.err==0){
                        result.add(response.mData.article);
                    }

                }else{
                    ZXLog.i(TAG,"url="+request.getUrl());
                }
                mLastCount.getAndIncrement();
                long now = System.currentTimeMillis();
                if(now/1000!=mLastTime.get()/1000){
                    if(response.mData!=null&&response.mData.err==0) {
//                        ZXLog.i(TAG, "duration  " +(System.currentTimeMillis()-time));
//                        ZXLog.i(TAG, "current id " + response.mData.article.id);
                    }
                    ZXLog.i(TAG,mLastCount+" items/second");
                    mLastTime.set(now);
                    mLastCount.set(0);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }
}
