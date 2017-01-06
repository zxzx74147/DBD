package com.zxzx74147.modules.utils;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhengxin on 2016/12/20.
 */
public class RegexUtils {
    private static HashMap<String ,Pattern> mCache = new HashMap<>();

    public static Matcher matchString(String line,String regex){
        // 创建 Pattern 对象
        Pattern r = null;
        if(mCache.containsKey(regex)){
            r = mCache.get(regex);
        }else{
            r = Pattern.compile(regex);
            mCache.put(regex,r);
        }
        if(line==null){
            return null;
        }

        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        if(m.find()) {
            return m;
        }
        return null;
    }
}
