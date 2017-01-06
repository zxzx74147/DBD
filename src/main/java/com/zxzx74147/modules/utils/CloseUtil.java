package com.zxzx74147.modules.utils;

import java.io.Closeable;

/**
 * Created by zhengxin on 15/7/7.
 */
public class CloseUtil {
    public static void close(Closeable closeable){
        try{
            closeable.close();
        }catch (Exception e){

        }
    }

    public static void close(AutoCloseable closeable){
        try{
            closeable.close();
        }catch (Exception e){

        }
    }
}
