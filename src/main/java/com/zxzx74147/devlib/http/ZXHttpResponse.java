package com.zxzx74147.devlib.http;

/**
 * Created by zhengxin on 16/8/21.
 */

public class ZXHttpResponse<T> {

    public ZXErrorData mError = new ZXErrorData();
    public T mData = null;

    private ZXHttpRequest mRequest = null;

    public void setRequest(ZXHttpRequest request) {
        mRequest = request;
    }

    public ZXHttpRequest getRequest() {
        return mRequest;
    }

    public boolean isSuccess(){
        if(mError.errno != 200){
            return false;
        }
        return true;
    }
}
