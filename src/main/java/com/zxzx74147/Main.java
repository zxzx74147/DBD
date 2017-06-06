package com.zxzx74147;

import com.zxzx74147.devlib.api.ApiConfig;
import com.zxzx74147.devlib.api.ApiServer;
import com.zxzx74147.devlib.http.ZXHttpClient;
import com.zxzx74147.devlib.log.ZXLog;
import com.zxzx74147.devlib.utils.CloseUtil;
import com.zxzx74147.modules_dbd.api.DBDApiHandler;
import com.zxzx74147.modules_dbd.auction.AuctionTask;
import com.zxzx74147.modules_dbd.db.DBService;
import com.zxzx74147.modules_dbd.info.GetInfoService;
import com.zxzx74147.modules_dbd.info.WriteInfoService;
import com.zxzx74147.modules_dbd.utils.*;
import com.zxzx74147.modules_qiushi.QiushiMain;

import java.sql.*;
import java.util.LinkedList;

/**
 * Created by zhengxin on 2016/12/19.
 */

public class Main {
    public static final Object mWaitObj = new Object();

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new ExitHandler());

        if (false) {
            QiushiMain.main();

        } else {
            DBService.sharedInstance().init();
            ZXHttpClient.setHook(new DBDHttpHook());


//            AuctionTask task1 = new AuctionTask(14985884,120,144);
////            AuctionTask task3 = new AuctionTask(14983627,120,141);
//            try{
//                synchronized (mWaitObj) {
//                    mWaitObj.wait();
//                }
//                return;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            int mStart = 0;
//            Connection mConn = DBService.sharedInstance().getConnection();
//            PreparedStatement preparedStatement = null;
//            ResultSet rs = null;
//            LinkedList<Integer> mInput = new LinkedList<>();
//            try {
//                preparedStatement = mConn.prepareStatement(SQL_RECHECK);
//                rs = preparedStatement.executeQuery();
//                while (rs.next()) {
//                    mStart = rs.getInt("id");
//                    mInput.add(mStart);
//                }
//                ZXLog.e("length=",""+mInput.size());
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } finally {
//                if (preparedStatement != null) {
//                    try {
//                        preparedStatement.close();
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                }
//                CloseUtil.close(mConn);
//            }

            mStart = 0;
            mStart=15486713-1;

            GetInfoService.start();
            WriteInfoService.start();

            GetInfoService.getExecutor().setOutputExecutor(WriteInfoService.getExecutor());
            ApiServer.init(new ApiConfig(),new DBDApiHandler());
//            for (int i = mStart; i >= 15453202-500; i--) {
//                try {
//                    GetInfoService.getExecutor().addInput(i);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            try {
//                synchronized (mWaitObj) {
//                    mWaitObj.wait();
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

    }

    private static class ExitHandler extends Thread {

        public void run() {
            System.out.println("Set exit");
            DBService.sharedInstance().close();
        }
    }
}
