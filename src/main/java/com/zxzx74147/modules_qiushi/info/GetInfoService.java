package com.zxzx74147.modules_qiushi.info;


/**
 * Created by zhengxin on 2016/12/21.
 */
public class GetInfoService {

    private static GetInfoExecutor mExecutor;

    public static void start() {
        mExecutor = new GetInfoExecutor(1, 1);
    }

    public static void stop() {
        mExecutor.shutdown();
    }

    public static GetInfoExecutor getExecutor(){
        return mExecutor;
    }
}
