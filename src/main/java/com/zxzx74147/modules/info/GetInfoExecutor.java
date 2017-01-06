package com.zxzx74147.modules.info;

import com.zxzx74147.devlib.http.ZXHttpRequest;
import com.zxzx74147.devlib.http.ZXHttpResponse;
import com.zxzx74147.devlib.log.ZXLog;
import com.zxzx74147.devlib.task.BaseExecutor;
import com.zxzx74147.modules.Config;
import com.zxzx74147.modules.info.data.BidListData;
import com.zxzx74147.modules.info.data.DBDData;
import com.zxzx74147.modules.info.data.PriceData;
import com.zxzx74147.modules.utils.HttpUtils;
import com.zxzx74147.modules.utils.RegexTable;
import com.zxzx74147.modules.utils.RegexUtils;
import com.zxzx74147.modules.utils.TimeUtils;
import com.zxzx74147.utils.ZXStringUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * Created by zhengxin on 2016/12/21.
 */
public class GetInfoExecutor extends BaseExecutor<Integer, DBDData> {
    private static final String TAG = GetInfoExecutor.class.getName();

    public GetInfoExecutor(int threadNum, int bufferNum) {
        super(threadNum, bufferNum, "GetInfoExecutor Thread");
        BLOCK_NUM = 1;
    }

    @Override
    public List<DBDData> process(List<Integer> input) {
        List<DBDData> result = new LinkedList<>();
        for(Integer id:input) {
            try {
//                ZXLog.i(TAG, "get info=" + id);
                DBDData resultData = new DBDData();
                resultData.id = id;
                int sku = 0;
                int sku2 = 0;

                {
                    ZXHttpRequest<String> request = new ZXHttpRequest<>(String.class);
                    request.setUrl(Config.HOST_HTML + id);
                    HttpUtils.addHeadersHTML(request);
                    ZXHttpResponse<String> response = request.sendSync();
                    Matcher m = RegexUtils.matchString(response.mData, RegexTable.REGEX_START);
                    if (m != null) {
//                        ZXLog.i("date", "date=" + m.group(1));
                        int time = (int) (TimeUtils.getTimestamp(m.group(1)) / 1000);
                        resultData.time = time;
                    }

                    m = RegexUtils.matchString(response.mData, RegexTable.REGEX_PACK_STATUS);
                    if (m != null) {
//                        ZXLog.i(TAG, "package=" + m.group(1));
                        resultData.pack_status = m.group(1);
                    }


                    Document document = Jsoup.parse(response.mData);
                    Elements elements = document.select(DecoderConfig.ABSTRACT);
                    if (elements.size() != 0) {
                        resultData.item_abs = elements.attr("title");
                    }
                    elements = document.select(DecoderConfig.JD_ITEM_ID);
                    if (elements.size() != 0) {
                        resultData.jd_item_id = Long.valueOf(elements.attr("value"));
                    }
                    elements = document.select(DecoderConfig.JD_ITEM_SKU);
                    if (elements.size() != 0) {
                        sku = Integer.valueOf(elements.attr("value"));
                    }
                }


                {
                    final ZXHttpRequest<PriceData> request = new ZXHttpRequest<>(PriceData.class);
                    request.setUrl(Config.HOST_AUCTION + Path.ADDRESS_PRICE);
                    request.addParam("paimaiId", id);
                    request.addParam("sku", resultData.jd_item_id);
                    HttpUtils.addHeaders(request);
                    ZXHttpResponse<PriceData> response = request.sendSync();
                    if (response.isSuccess()) {
                        resultData.jd_price = response.mData.jdPrice;

                    }
                }

                {
                    final ZXHttpRequest<BidListData> request = new ZXHttpRequest<>(BidListData.class);
                    request.setUrl(Config.HOST + Path.ADDRESS_INFO);
                    request.addParam("paimaiId", id);
                    request.addParam("skuId", sku);
                    request.addParam("t", "759781");
                    request.addParam("start", "0");
                    request.addParam("end", "1");
                    request.addParam("callback", "jQuery2986246");
                    request.addParam("_", System.currentTimeMillis());
                    HttpUtils.addHeaders(request);
                    ZXHttpResponse<BidListData> response = request.sendSync();
                    if(response.isSuccess()) {
                        resultData.deal_price = response.mData.getCurrentPrice();
                        resultData.status = response.mData.getAuctionStatus();
                        if (response.mData.getBidList().size() > 0) {
                            resultData.deal_price = response.mData.getBidList().get(0).getPrice();
                            if(ZXStringUtil.checkString(response.mData.getBidList().get(0).getUserName1())) {
                                resultData.deal_user = response.mData.getBidList().get(0).getUserName1();
                            }else{
                                resultData.deal_user = response.mData.getBidList().get(0).getUsername();
                            }
                        }
                    }
                }
                result.add(resultData);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }
}
