package com.zxzx74147.modules_dbd.utils;

import com.zxzx74147.devlib.http.ZXHttpRequest;

/**
 * Created by zhengxin on 2016/12/20.
 */
public class HttpUtils {
    private static final String UA="Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36";
    private static final String COOKIE="user-key=c649e301-a63a-48f0-a34b-e6f6298484b1; unpl=V2_ZzNtbUdeQBF0WhFSfhBYDWIDGg9KVUZGfV1CXHNOWQVlAkBZclRCFXMUR1NnGVsUZwYZWEVcQx1FCEdkexhdBGYKGlRKVXMVcQ8oVRUZVQAJbRotNTcxSjFuOSgkEQQGOlZtIAM8cyV2CHZdSxlZDGQGE1lBUEsXfQpGVXkZXwJmBhNdcmdEJXUAQlF%2fGV4DV9S785Tb5cHMopHm%2bSlYA2QFFlxDVkAldDhHZCl3hLPR27fsXoPpmqCA0Rl7HFUGYgIWXkVfQR13CEdWexpbBGICEm1DZ0A%3d; __jdv=122270672|www.linkstars.com|t_1000089893_156_0_184__9AFQCnuWNMn9i2ldNLpZ|tuiguang|48250cf74859419c934b9d599f4130c5|1497595580047; mt_xid=V2_52007VwMXW15dU14cThFeDWUDE1BdW1VbGkgZbAw3BBBVCFwBRkpAHgsZYlETU0ELBwkWVU1bAm4CRVRfXAUIT3kaXQVhHxNVQVhUSxxLEl4HbAARYl9oUmofSRBZBWAFEFttWFdcGA%3D%3D; cn=1; ipLocation=%u5317%u4EAC; areaId=1; ipLoc-djd=1-2800-2849-0.138496194; wlfstk_smdl=qp5vvcjzcletl6wrjy22i2qdawf0n3rd; _jrda=1; TrackID=1Hn0JNEKvqaurCXetF_KRd0XgSx6cDUY4nXnxkyi3EHuozGXMZ7lwsi7F13YG4sSAF8ZQq5LOvuto0PYyn88zhorPj8xRSNDnbPrlFQDYOXFwS5ixDu2w5px09TPZoyxW; pinId=LmjrGgv2g9tA9AXplivx1A; pin=zxzx74147; unick=zxzx74147; _tp=lYlPrmsaYsiA52syaPk0rg%3D%3D; _pst=zxzx74147; ceshi3.com=201; thor=B4E97DECC587B5355C65D65F70592851F295DF0C7DA8FA4A7CAD11DDBFEADD7DA7F56CC86A2F773F314DC423CF916F1ACFB7046A658C9CF00AE43AA4B3DA9D8CEE4B0CBD4C78A85D1989328299FDE2561774CE48364CA2C7647D5647E791FCA0D9A37C513F268A05A934D321CE1EB589C3AA87B7D796F5E9731D11745EF18B560635C88296CFCB1D81433DF7F0D6B4B5; __jda=122270672.14824052693931031260401.1482405269.1497855535.1497859529.476; __jdb=122270672.16.14824052693931031260401|476.1497859529; __jdc=122270672; 3AB9D23F7A4B3C9B=CIIS76J5ECHZG5KBK6QZVOISEMG4HH2N44AU7SGSPYWMRHGFS7WHAG3J5CTEFQB5RFZFVHV4ENDVHFOCMERWTVUFP4; __jdu=14824052693931031260401";
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
