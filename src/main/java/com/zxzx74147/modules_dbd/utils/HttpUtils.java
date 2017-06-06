package com.zxzx74147.modules_dbd.utils;

import com.zxzx74147.devlib.http.ZXHttpRequest;

/**
 * Created by zhengxin on 2016/12/20.
 */
public class HttpUtils {
    private static final String UA="Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36";
    private static final String COOKIE="mt_xid=V2_52007VwMXW15dU14cThFeDWUDE1BdW1VbGkgZbFZvVxVRCQ1WRhhMG10ZYlZGVkEIVg5KVUoJAzUCRlIKWlpTGXkaXQVhHxNXQVlUSx9KElkAbAEVYl9oUWobSh9cAGAzElZc; unpl=V2_ZzNtbRdXEUF0WhQDKEpeUWIFE1gRUhMcIAwRAS4eXFJhVxFbclRCFXMUR1FnGFoUZwYZWEJcQhFFCEdkexhdBGYKGlRKVXMVcQ8oVRUZVQAJbUFZNTwxXSJeLQo8aRhEVzMRXXJecxVwAUVReh1fAm8BGl9CVkEVdg9HUXoZbDVgMxBZQVVAEX04kf3Vz9Cjs7q4ivDVcxFzC0BQehhdBlcCIlxyAS0VdAlHVX0QXgxnThJYS1RGFHELQVx5EV4FZgESXkVWRhR1OEdkeA%3d%3d; __jdv=122270672|www.linkstars.com|t_1000089893_156_0_184__b5FZCyfgZovAup|tuiguang|d1ce0ccfbb3e4704b4a8d5fdd61f7e27|1491547266017; areaId=1; user-key=891e8b58-8a35-439c-997a-5e7e680b65be; cn=2; ipLoc-djd=1-2800-2848-0.138496194; ipLocation=%u5317%u4EAC; TrackID=1bqgys6Z4VGuoy6SnfSc1vNkz8B-SoXiiSdwGeX_HYSMKfua53Zwl-IuRdGQTkGsuFM3_Ff0aNmEbWnguMjhQm9-RYI5I7OafgnthlFe5CgXacG2GwLnacOKmWUKjo5nX; pinId=LmjrGgv2g9tA9AXplivx1A; pin=zxzx74147; unick=zxzx74147; _tp=lYlPrmsaYsiA52syaPk0rg%3D%3D; _pst=zxzx74147; ceshi3.com=201; thor=CDE475E9F33A740737B34CB1D226C133A578AA152DA59519BA751E6E8D2332C8BB3038D99C79F9EF96CF7E6ACEF811A1F0EB48C6E5656D20B81F21B44DE655CA7860E98B23C9D9FD0993C02A71A07216FD7043B1C358A58A3F5669C6366D30ACE210283870738F9AD8AB6DB6F6D1841022DB7BC1E37D35D971E076B74D57CFEFE005B180611CCABD53FF6B08BBB019E3; __jda=122270672.14824052693931031260401.1482405269.1491748131.1491795732.270; __jdb=122270672.64.14824052693931031260401|270.1491795732; __jdc=122270672; __jdu=14824052693931031260401";

    private static final String COOKIE_NOUSER="__jdv=122270672|direct|-|none|-|1482372808221; __jda=122270672.1482372808219258562135.1482372808.1482372808.1482372808.1; __jdb=122270672.2.1482372808219258562135|1.1482372808; __jdc=122270672; __jdu=1482372808219258562135";
    public static final void addHeaders(ZXHttpRequest request){
        request.addHeader("Accept","*/*");
//        request.addHeader("Accept-Encoding","gzip, deflate, sdch, br");
        request.addHeader("Accept-Language","zh-CN,zh;q=0.8");
        request.addHeader("User-Agent",UA);
        request.addHeader("Cookie",COOKIE_NOUSER);
    }

    public static final void addUserHeaders(ZXHttpRequest request){
        request.addHeader("Cookie",COOKIE);
    }

    public static final void addHeadersHTML(ZXHttpRequest request){
        request.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        request.addHeader("Accept-Language","zh-CN,zh;q=0.8");
        request.addHeader("User-Agent",UA);
//        request.addHeader("Cookie",COOKIE);
    }
}
