package com.zxzx74147.devlib.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhengxin on 2017/6/6.
 */
public class UrlUtil {

    public static Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<String, String>();
        String[] temp = query.split("\\?");
        if (temp != null && temp.length > 1) {
            query = temp[1];
        }
        for (String param : query.split("&")) {
            String pair[] = param.split("=");
            if (pair.length > 1) {
                result.put(pair[0], pair[1]);
            } else {
                result.put(pair[0], "");
            }
        }
        return result;
    }

    public static String queryToAddress(String query) {
        String[] temp = query.split("\\?");
        if (temp == null) {
            return query;
        }
        return temp[0];
    }
}
