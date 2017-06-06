package com.zxzx74147.modules_qiushi.http;

import com.zxzx74147.devlib.http.ZXHttpHook;
import com.zxzx74147.devlib.http.ZXHttpRequest;
import com.zxzx74147.devlib.http.ZXHttpResponse;
import com.zxzx74147.utils.ZXTimeStampGenerator;
import com.zxzx74147.utils.ZXUniqueIDGenerator;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by zhengxin on 2016/12/20.
 */
public class QiushiHttpHook implements ZXHttpHook {
    @Override
    public void onSendRequest(ZXHttpRequest request) {
        HttpUtils.addHeaders(request);
        request.addParam("r","f0a8601c"+ ZXTimeStampGenerator.getTimeStamp());
        request.addParam("rqcnt", ZXUniqueIDGenerator.getUniqueID());
        if(ZXUniqueIDGenerator.getUniqueID()>1002){
            ZXUniqueIDGenerator.reset();
        }
    }

    @Override
    public String onResponse(ZXHttpResponse response, Response rsp) {

        String data = "";

        try {
            data = rsp.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        if(String.class == response.getRequest().getType() ){
//            return data;
//        }
//        int start = data.indexOf("({");
//        int end = data.lastIndexOf("})");
//        if(start>0&&end>start) {
//            data = data.substring(start+1, end +1);
//        }
        return data;
    }


}
