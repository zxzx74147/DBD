package com.zxzx74147.devlib.http;


import com.zxzx74147.devlib.log.ZXLog;
import com.zxzx74147.utils.ZXJsonUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.zxzx74147.devlib.http.ZXHttpConfig.HTTP_ERROR;

/**
 * Created by zhengxin on 16/8/21.
 */

public class ZXHttpRequest<T> {
    private static final String TAG = ZXHttpRequest.class.getName();
    private static HashMap<Integer, List<ZXHttpRequest>> mRequests = new HashMap<>();


    private ZXHttpConfig.HTTP_METHOD mMethod = ZXHttpConfig.HTTP_METHOD.HTTP_GET;

    private HashMap<String, String> mParams = new HashMap<>(10);

    private String mUrl = null;

    private String mContentType = null;

    private Class<T> tType = null;

    private Call mCall = null;

    private HashMap<String, String> mHeader = new HashMap<>(5);

    private ZXHttpCallback<T> mCallback = null;

    private int mTag = 0;

    private ZXHttpRequest() {
    }

    private int getTag() {
        return mTag;
    }

    public void setTag(int tag) {
        mTag = tag;
    }

    public ZXHttpRequest(Class<T> type) {
        tType = type;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public void setMethod(ZXHttpConfig.HTTP_METHOD method) {
        mMethod = method;
    }

    public void addParam(String key, String value) {
        mParams.put(key, value);
    }

    public void addParam(String key, Object value) {
        mParams.put(key, value.toString());
    }

    public void addHeader(String key, String value) {
        mHeader.put(key, value);
    }

    public ZXHttpConfig.HTTP_METHOD getMethod() {
        return mMethod;
    }

    public String getUrl() {
        return mUrl;
    }

    public HashMap<String, String> getParams() {
        return mParams;
    }


    public ZXHttpResponse sendSync(){
        long time= 0;
        time = System.currentTimeMillis();
        Response rsp = ZXHttpClient.sendRequestSync(this);
        long diff = System.currentTimeMillis()-time;
        time = System.currentTimeMillis();

        if(rsp==null){
            final ZXHttpResponse<T> response = new ZXHttpResponse<T>();
            response.setRequest(ZXHttpRequest.this);
            response.mError.errno = HTTP_ERROR;
            response.mError.errmsg = "network error";
            return response;
        }else{
            final ZXHttpResponse<T> response = new ZXHttpResponse<T>();
            response.setRequest(ZXHttpRequest.this);

            String rspString = "";
            if(ZXHttpClient.mHook!=null){
                rspString = ZXHttpClient.mHook.onResponse(response,rsp);
            }else{
                try {
                    rspString = rsp.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            T data = ZXJsonUtil.fromJsonString(rspString, tType);
            response.mError.errno = 200;
            response.mData = data;
            long diff2 = System.currentTimeMillis()-time;
//            ZXLog.i(TAG,"http time="+diff+"|parser time="+diff2);
            return response;

        }

    }

    public void send(ZXHttpCallback<T> callback) {
        mCallback = callback;
        mCall = ZXHttpClient.sendRequestAsync(this);
        final ZXHttpResponse<T> response = new ZXHttpResponse<T>();
        if (mTag != 0) {
            List<ZXHttpRequest> list = mRequests.get(mTag);
            if (list == null) {
                list = new LinkedList<>();
                mRequests.put(mTag, list);
            }
            list.add(this);
        }
        final List<ZXHttpRequest> mList = mRequests.get(mTag);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (mList != null) {
                    mList.remove(ZXHttpRequest.this);
                }
                if (call.isCanceled()) {
                    return;
                }
                final ZXHttpResponse<T> response = new ZXHttpResponse<T>();
                response.setRequest(ZXHttpRequest.this);
                response.mError.errno = HTTP_ERROR;
                response.mError.errmsg = e.getMessage();
                if (mCallback != null) {
                    mCallback.onResponse(response);
                }
            }

            @Override
            public void onResponse(Call call, final Response rsp) throws IOException {
                if (mList != null) {
                    mList.remove(ZXHttpRequest.this);
                }
                if (call.isCanceled() || mCallback == null) {
                    return;
                }
                final ZXHttpResponse<T> response = new ZXHttpResponse<T>();
                response.setRequest(ZXHttpRequest.this);

                String rspString = null;
                if(ZXHttpClient.mHook!=null){
                    rspString = ZXHttpClient.mHook.onResponse(response,rsp);
                }else{
                    rspString = rsp.body().string();
                }
                T data = ZXJsonUtil.fromJsonString(rspString, tType);
                response.mError.errno = 200;
                response.mData = data;

                if (mCallback != null) {
                    mCallback.onResponse(response);
                }


            }
        });
    }

    public Class<T> getType(){
        return tType;
    }

    public void cancel() {
        if (mCall != null) {
            mCall.cancel();
        }
        mCallback = null;
    }

    public static void cancelWithTag(int tag) {
        List<ZXHttpRequest> list = mRequests.get(tag);
        if (list == null) {
            return;
        }
        for (ZXHttpRequest request : list) {
            request.cancel();
        }
        list.clear();
        mRequests.remove(tag);
    }

    public HashMap<String, String> getHeader() {
        return mHeader;
    }
}
