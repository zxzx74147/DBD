package com.zxzx74147.modules.utils;

/**
 * Created by zhengxin on 2016/12/20.
 */
public class RegexTable {
//    public static final String REGEX_START="结束时间：<[^>]+><[^>]+><[^>]+>[0-9]{4}-[0-9]{2}-[0-9]{2}  [0-9]{2}:[0-9]{2}:[0-9]{2}";

    public static final String REGEX_START="结束时间：[<[^>]+>]*([0-9]{4}-[0-9]{2}-[0-9]{2}  [0-9]{2}:[0-9]{2}:[0-9]{2})";
    public static final String REGEX_DATE="[0-9]{4}-[0-9]{2}-[0-9]{2}  [0-9]{2}:[0-9]{2}:[0-9]{2}";
//    public static final String REGEX_PACK_STATUS="[<[^>]+>]{1}包装外观：[<[^>]+>]{1}([^<>]*)[<[^>]+>]{1}";
    public static final String REGEX_PACK_STATUS="包装外观：<[^>]+>([^>]+)<[^>]+>";

}
