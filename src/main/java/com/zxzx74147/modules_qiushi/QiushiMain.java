package com.zxzx74147.modules_qiushi;

import com.zxzx74147.Main;
import com.zxzx74147.devlib.http.ZXHttpClient;
import com.zxzx74147.modules_qiushi.db.DBService;
import com.zxzx74147.modules_qiushi.http.QiushiHttpHook;
import com.zxzx74147.modules_qiushi.info.GetInfoService;
import com.zxzx74147.modules_qiushi.info.WriteInfoService;

/**
 * Created by zhengxin on 2017/1/6.
 */
public class QiushiMain {
    public static Object mWaitObj = new Object();
    public static void main(){
        Runtime.getRuntime().addShutdownHook(new ExitHandler());
        DBService.sharedInstance().init();

        ZXHttpClient.setHook(new QiushiHttpHook());
        GetInfoService.start();
        WriteInfoService.start();
        GetInfoService.getExecutor().setOutputExecutor(WriteInfoService.getExecutor());
        //118324708
        int start = 118547093;
        for (int i = start; i > start-100000; i--) {
            try {
                GetInfoService.getExecutor().addInput(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            synchronized (mWaitObj) {
                mWaitObj.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class ExitHandler extends Thread {
        public void run() {
            System.out.println("Set exit");
            DBService.sharedInstance().close();
        }
    }
}
