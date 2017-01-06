package com.zxzx74147.utils;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.zxzx74147.devlib.log.ZXLog;

/**
 * Created by zhengxin on 16/1/10.
 */
public class ZXJsonUtil {
    private static String TAG = "ZXJsonUtil";
    public static String toJsonString(Object object) {
        if(object==null){
            return "null";
        }
        return JSON.toJSONString(object);
    }

    public static <T> T fromJsonString(String json, Class<? extends T> mClass) {
        try {
            if(mClass == String.class){
                return (T) json;
            }

            return new Gson().fromJson(json,mClass);
        } catch (Exception e) {
            ZXLog.e(TAG,"error json="+json);
            e.printStackTrace();
        }
        return null;
    }
}
