package com.zxzx74147.devlib.http;

/**
 * Created by zhengxin on 16/8/21.
 */

public interface ZXHttpCallback<T> {
    void onResponse(ZXHttpResponse<T> response);
}
