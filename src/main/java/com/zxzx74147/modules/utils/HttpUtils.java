package com.zxzx74147.modules.utils;

import com.zxzx74147.devlib.http.ZXHttpRequest;

/**
 * Created by zhengxin on 2016/12/20.
 */
public class HttpUtils {
    private static final String UA="Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36";
//    private static final String COOKIE="webp=1; visitkey=38889208062073699; buy_uin=422583341; jdpin=zxzx74147; nickname=%E5%B0%8F%2Faiq%E6%96%B0; pinsign=9c572c71feed6714f887c09dce7aab91; __wga=1479469431335.1479468926933.1479124659253.1478679394766.3.3; dmpjs=dmp-d389213cded7dbe7f524b3516824773bb01771; user-key=0bdd49dd-3958-45b9-9a03-2511fbcca70f; _jrda=7; TrackID=1kgj14mh4r_DzJTT5PSiTGJJ2mRq0dXxvL6uWKI3u69WsNP4b9AyD3BrKy9w8HUKucXz0HyD-jzE0xaZi3j1jf6Q_1_0j0phOXRK4RtiH__prWDiDyjSuRbUWy5KKf308; pinId=LmjrGgv2g9tA9AXplivx1A; pin=zxzx74147; unick=zxzx74147; _tp=lYlPrmsaYsiA52syaPk0rg%3D%3D; _pst=zxzx74147; ceshi3.com=Mn79cXNag4-mZcbI2Fe9RhulSnM6XvnKvkGPs88XqMI; __jdv=122270672|go.smzdm.com|t_4298_b5FZCNugzWGvcL5DkRVE|tuiguang|6974afbf1ba842d89dafeda3acad98a7|1482139930826; mt_xid=V2_52007VwMXV11aW1kdSRpsAzIBRwVcXQVGFh4bDxliBRZQQVBSCR5VHwhXZlRAAl5dUlpLeRpdBWAfElFBWFpLH08SXgZsAxBiX2hRahtKH1wAYDMSVlw%3D; OUTFOX_SEARCH_USER_ID_NCOO=1083772977.4402933; ___rl__test__cookies=1482223269807; ipLoc-djd=1-2800-2850-0; cn=7; _distM=47802998722; 3AB9D23F7A4B3C9B=CIIS76J5ECHZG5KBK6QZVOISEMG4HH2N44AU7SGSPYWMRHGFS7WHAG3J5CTEFQB5RFZFVHV4ENDVHFOCMERWTVUFP4; unpl=V2_ZzNtbUFQFx1zAURTex4MB2IGQVoSUEYSJlhEVSgfVABvARYJclRCFXIURldnGl0UZAMZXUNcQRNFCHZXfBpaAmEBFl5yAR1LI1USFi9JH1c%2bbUgbF0tAEn0KQVxyGVgHbzMiXkJnQxRFCENRextVBmUDEW1yUHMlRQxHV3MdWjVmMxNtAAMfFnYOQFV%2fVFwAYgMQVEFVQxZFCXZX; CCC_SE=ADC_3Cdc26YwwXHtXNdrz5tawrmn5lN1T0mVUq5EyvT%2bU4DZ0N0W3P%2fkHfqvSge8IFwQSdOGlP16VE2fVWuyz%2b%2fFaZLDiv%2fj9wJ5tEef8aaHVdESCSNH0uWbFHl4akq%2b8XuDw%2fZlgfDVQaYbDdjDIZney6Xt2EqKm9eRqx4xDTLi4AMaoZW%2fJBi21pXSsl37MBKYoGGxNa%2bA4zJOx0pGuFiM5RyKo%2fBCiYHVzNn2yWHzaKGqrCJzT%2b8Sw0xeppsTuERHfk%2fsdDeOHJ78t7sck%2b6KsZKcuzi0IRCuWKOcqIOdFbdC9bFVv%2bRMFO1yRp5u%2bY3zmmWVYyB4wt1NyUA1uff1H7FuZMyJ5%2fiAYXho3OYnjFPiA5zTMigCc7G0P30nmjYWvCSpawIjle1IQHhYF1e6zIGowszUSaF9IRje34DJL2F%2fRG%2ffMDxgaOpOORPiJ5gkcPqT7e%2bVaKn9bjeqFwDMx%2botX0YORkfVbBRpD3WEyK686xE6RArosbeaFDB9wqq4fNfXFoBeS%2f5q9TkquAOeM%2bNNCaBzUmveo%2boH0IM7OeI%2fUXUhpQ5Lm1fLo3jYXBZqw2KiW5rpjVPlFZL7IGg4RXHFFDWY9dDo5jcQaXMQ%2fjYIgE%2f5TCvCXPXdMx7GwAuY6l3mX98ZUCfAjpE8NvZsdQDldrJkeVSSqPdgLJkEyD96sWZG%2bRY%2bgNLR7uPkAM43; ipLocation=%u5317%u4EAC; areaId=1; thor=530C74D71FD992BFE3EF13E8E37BCE03F24BE69235CAE2B0E6C2B324405D11D70120C6B10BFBD3D4EFE4901608C46A9CC4E28F448E0CBD2A5688470C570B25B13B4BDABE9F7A7B1199897BBCA8A1D92BCA37777D4C30B221CC2CCD3438A5C3D72506E7329949A1CC3571311EB602445319D714E7A41F35AF68432B0766D0F67D084A70B6374E4A28CAC10C699ADFBA57; __jda=122270672.1441382312.1471181150.1482239438.1482291661.226; __jdb=122270672.7.1441382312|226.1482291661; __jdc=122270672; __jdu=1441382312";
    private static final String COOKIE="TrackID=1-9Oc81qvifn7oSi1MHpUSTcg3P0GGCHQpajMA6lzTDLH4hzWD9LbmlCcDhoi-Px3tFD-Hw89i62aqdFGpJEG44ighE1O_xTZam4ftyf3cGd2xJjW2At-Xl3tqwzz6lMI; pinId=LmjrGgv2g9tA9AXplivx1A; pin=zxzx74147; unick=zxzx74147; _tp=lYlPrmsaYsiA52syaPk0rg%3D%3D; _pst=zxzx74147; JSESSIONID=D774CDCE03FC4DF87D360D2FF701708F.s1; unpl=V2_ZzNtbRIFExB0XBFSfhFUDGJQQF5KVkJBfVsTVikaXgEyUBQKclRCFXIURldnGlgUZwAZWEFcQBZFCHZXfBpaAmEBFl5yAR1LI1USFi9JH1c%2bbUgbF0tAFnAKRV19EVsAZjMiXkJnQxRFCENdeBxdAWQEGl9KVUMUdwhFU3ocXQVXMxVtcmdBF3ABR1dLGGwEV0FGAUdSQRdyCAtUfhBfAGYHEVpKVUsXdQlEVHgeXQBmAyJcclQ%3d; CCC_SE=ADC_3YqafIwtv6hgd%2fpoQ9mzrPLgKuz8PnlUekQb2EvcupXFOnyuWZTGH4ddPAcBaeQ6R2nF2SPA4G37RN6y6hfiWivOckbIvUniC3wGie8hho24SfDUkaLSjX545APGTn5zEd7Lm1GWDTp5lO5%2bVLWQwfOPBEbdLSjqKGM7W5jphrJcIWdZZAWOOYCF8oHaFGXc50cwJKnMlA8JrSF0OmD3N8dSlZ%2f0wuW41vjhIUNDQdSQdDxPtS687XjEu3cY7RL0zZ92giogj2NQJS2QEuSsD%2fKEfzfkAqRCCpU6ssuOYRstz8gFQFYeqhIh9%2bOA0rS%2fJABQDhuB2tPEF6fNG4lXJQIp9fStbBwMf3zjRO4FM%2bdQcUpn6f%2fCZ9aZQnBb%2b2z16qa8DURvmOdqt9bHPFI%2fLF3of4Lmr82gb00UpAF74n7RG19Q1zgY3mS436U08CisjYjYFzZlYFEwrDDrDWrFVQfbOgKWCu1eh%2fuzX1NRfwvwVI59roU3a4aNfKzoubCGKCd91IBpyAdK1JXkW05jzo5Zs1CVgkFxt23NFHMYE7Gff1pfa0Uh5acykZAl10m1ehNkQo3bBwYhnQwOhrmYIg%3d%3d; __jdv=122270672|google-search|t_262767352_googlesearch|cpc|kwd-22432879640_0_aca40ef749984bc2900e9bd3c235db7f|1482640943008; areaId=1; OUTFOX_SEARCH_USER_ID_NCOO=1344619336.8204694; _jrda=3; user-key=4846c450-6d33-466b-8fc2-ce1ae44fad7c; cn=9; ipLoc-djd=1-2800-2849-0.137968829; ipLocation=%u5317%u4eac; 3AB9D23F7A4B3C9B=CIIS76J5ECHZG5KBK6QZVOISEMG4HH2N44AU7SGSPYWMRHGFS7WHAG3J5CTEFQB5RFZFVHV4ENDVHFOCMERWTVUFP4; __jda=122270672.14824052693931031260401.1482405269.1482751690.1482754823.23; __jdb=122270672.2.14824052693931031260401|23.1482754823; __jdc=122270672; thor=5DC6AE0E364CD2D711039A280811B9A3A2ABFD2C0B48A8F7B20B9BAAD561498ED5E589EC16A76B8B7DD31ED933D8CAD0333D2A4FD18CA93E59F7DA603A198AECA68F36E98C4ED59DE30CB6387AE2F29D60B2FF6A1AFF50F0C694A9B7BE2501A14C2A00BCF14B093B9EB0441AB00061FB8BFB58E871B85E6E371E5815F12C42F440E2DE872B4435ADD389B294E8CAA78B; __jdu=14824052693931031260401";
    private static final String COOKIE_NOUSER="__jdv=122270672|direct|-|none|-|1482372808221; __jda=122270672.1482372808219258562135.1482372808.1482372808.1482372808.1; __jdb=122270672.2.1482372808219258562135|1.1482372808; __jdc=122270672; __jdu=1482372808219258562135";
    public static final void addHeaders(ZXHttpRequest request){
        request.addHeader("Accept","*/*");
        request.addHeader("Accept-Encoding","gzip, deflate, sdch, br");
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
