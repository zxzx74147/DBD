package com.zxzx74147.devlib.log;

/**
 * Created by zhengxin on 16/8/21.
 */

public class ZXLog {

    public static void i(String tag, String message) {
        String log = String.format("TAG:%s message:%s", tag, message);
        System.out.println(log);

    }

    public static void e(String tag, String message) {
        String log = String.format("TAG:%s message:%s", tag, message);
        System.out.println(log);

    }
}
