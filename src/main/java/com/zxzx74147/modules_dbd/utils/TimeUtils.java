package com.zxzx74147.modules_dbd.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhengxin on 2016/12/20.
 */
public class TimeUtils {
    private static final long SECOND = 1000;
    private static final long MINUTE = SECOND * 60;
    private static final long HOUR = MINUTE * 60;
    private static final long DAY = HOUR * 24;

    public static long getTimestamp(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf.parse(dateString);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static String getTimeDuration(long duration) {
        String result = "";
        if (duration > DAY) {
            result = result + duration / DAY + "天";
            duration %= DAY;
        }
        if (duration > HOUR) {
            result = result + duration / HOUR + "时";
            duration %= HOUR;
        }
        if (duration > MINUTE) {
            result = result + duration / MINUTE + "分";
            duration %= MINUTE;
        }
        if (duration > SECOND) {
            result = result + duration / SECOND + "秒";
            duration %= SECOND;
        }
        return result;
    }
}
