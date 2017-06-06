package com.zxzx74147.modules.base;


import com.zxzx74147.modules_qiushi.info.GetInfoExecutor;

/**
 * Created by zhengxin on 2016/12/21.
 */
public class GetInfoBaseService {

    private static GetInfoBaseExecutor mExecutor;

    public static void start() {
//        mExecutor = new GetInfoBaseExecutor(1, 1);
    }

    public static void stop() {
        mExecutor.shutdown();
    }

    public static GetInfoBaseExecutor getExecutor(){
        return mExecutor;
    }


}
