package com.zxzx74147.modules.utils;

import com.zxzx74147.devlib.http.ZXHttpHook;
import com.zxzx74147.devlib.http.ZXHttpRequest;
import com.zxzx74147.devlib.http.ZXHttpResponse;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by zhengxin on 2016/12/20.
 */
public class DBDHttpHook implements ZXHttpHook {
    @Override
    public void onSendRequest(ZXHttpRequest request) {
//        HttpUtils.addHeadersHTML(request);
    }

    @Override
    public String onResponse(ZXHttpResponse response, Response rsp) {

        String data = "";
        try {
            data = rsp.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(String.class == response.getRequest().getType() ){
            return data;
        }
        int start = data.indexOf("({");
        int end = data.lastIndexOf("})");
        if(start>0&&end>start) {
            data = data.substring(start+1, end +1);
        }
        return data;
    }


}
