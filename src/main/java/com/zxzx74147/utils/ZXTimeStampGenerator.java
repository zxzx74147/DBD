package com.zxzx74147.utils;

/**
 * Created by zhengxin on 15/9/15.
 */
public class ZXTimeStampGenerator {

    public static synchronized long getTimeStamp(){
        return System.currentTimeMillis();
    }
}
