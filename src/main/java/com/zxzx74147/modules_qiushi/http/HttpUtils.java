package com.zxzx74147.modules_qiushi.http;

import com.zxzx74147.devlib.http.ZXHttpRequest;

/**
 * Created by zhengxin on 2016/12/20.
 */
public class HttpUtils {
    private static final String UA="qiushibalke_10.7.1_WIFI_auto_6";
    private static final String SOURCE="android_10.7.1";
    private static final String MODEL="google/shamu/shamu:7.0/NBD90Z/3264873:user/release-keys";
    private static final String IMEI="IMEI_b129aed9b05ccd2b60bee46ff0a8601c";
    private static final String DEVICE_INFO = "{\"DEVICEID\":\"355470062561036\",\"ANDROID_ID\":\"5b499cb0048135cb\",\"SDK_INT\":24,\"SERIAL\":\"ZX1G42BCLL\",\"MAC\":\"02:00:00:00:00:00\",\"RANDOM\":\"\"}";

    public static final void addHeaders(ZXHttpRequest request){
        request.addHeader("User-Agent",UA);
        request.addHeader("Source",SOURCE);
        request.addHeader("UuId",IMEI);
        request.addHeader("DeviceIdInfo",DEVICE_INFO);
        request.addHeader("Model",MODEL);

    }




}
