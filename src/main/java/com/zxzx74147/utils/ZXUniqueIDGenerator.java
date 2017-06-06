package com.zxzx74147.utils;

/**
 * Created by zhengxin on 15/9/15.
 */
public class ZXUniqueIDGenerator {
    private static int mStartID = 1;

    public static synchronized int getUniqueID(){
        return mStartID++;
    }

    public static void reset() {
        mStartID = 1;
    }
}
