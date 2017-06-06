package com.zxzx74147.devlib.http;

import com.zxzx74147.devlib.log.ZXLog;
import com.zxzx74147.utils.ZXStringUtil;

import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.*;
import okhttp3.internal.http.BridgeInterceptor;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;

/**
 * Created by zhengxin on 16/8/21.
 */
@SuppressWarnings("unchecked")
public class ZXHttpClient {


    private final static String TAG = "ZXHttpClient";
    private static OkHttpClient mClient = null;
    public static ZXHttpHook mHook = null;

    public static void setHook(ZXHttpHook hook) {
        mHook = hook;
    }

    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectionPool(new ConnectionPool(200, 15, TimeUnit.SECONDS));
        builder.followRedirects(true);
        builder.proxySelector(ZXProxySelector.sharedInstance());
        mClient = builder.build();
    }

    public static Response sendRequestSync(ZXHttpRequest request) {
        Request okHttpRequest = genarateRequest(request);
        Call call = mClient.newCall(okHttpRequest);
        try {
            return call.execute();
        } catch (IOException e) {
            ZXLog.i(TAG, "error url = " + request.getUrl());
            e.printStackTrace();
            return null;
        }
    }

    public static Call sendRequestAsync(ZXHttpRequest request) {
        Request okHttpRequest = genarateRequest(request);
        Call call = mClient.newCall(okHttpRequest);
        return call;
    }

    private static Request genarateRequest(ZXHttpRequest request) {
        if (mHook != null) {
            mHook.onSendRequest(request);
        }
        String url = request.getUrl();
        if (!ZXStringUtil.checkString(url)) {
            ZXLog.e(TAG, "url is null!!");
            return null;
        }
        Request.Builder builder = new Request.Builder();
        StringBuffer sb = new StringBuffer(40);
        Set<Map.Entry<String, String>> set = request.getParams().entrySet();
        for (HashMap.Entry<String, String> entry : set) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
        }

        Set<Map.Entry<String, String>> headers = request.getHeader().entrySet();
        for (HashMap.Entry<String, String> entry : headers) {
            builder.addHeader(entry.getKey(), entry.getValue());
        }
        String param = sb.toString();
        switch (request.getMethod()) {
            case HTTP_GET:
                if (ZXStringUtil.checkString(param)) {
                    url = url + "?" + sb.toString();
                }
                break;
            case HTTP_POST:
//                builder.post(RequestBody.create())
                break;
        }
//        ZXLog.i(TAG,"url="+url);
        builder.url(url);
        builder.get();
        Request okHttpRequest = builder.build();
        return okHttpRequest;
    }

    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            return originalResponse.newBuilder()
                    .build();
        }
    };


}
