package com.zxzx74147.devlib.http;

import okhttp3.Response;

/**
 * Created by zhengxin on 16/9/5.
 */

public interface  ZXHttpHook {
     void onSendRequest(ZXHttpRequest request);
     String onResponse(ZXHttpResponse response, Response rsp);
}
