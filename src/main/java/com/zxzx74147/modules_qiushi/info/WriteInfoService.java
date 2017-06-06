package com.zxzx74147.modules_qiushi.info;

/**
 * Created by zhengxin on 2016/12/21.
 */
public class WriteInfoService {

    private static WriteInfoExecutor mExecutor;

    public static void start() {
        mExecutor = new WriteInfoExecutor(3, 400);
    }

    public static void stop() {
        mExecutor.shutdown();
    }

    public static WriteInfoExecutor getExecutor(){
        return mExecutor;
    }
}
