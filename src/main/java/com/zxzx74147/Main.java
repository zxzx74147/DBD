package com.zxzx74147;

import com.zxzx74147.devlib.http.ZXHttpClient;
import com.zxzx74147.modules.auction.AuctionTask;
import com.zxzx74147.modules.db.DBService;
import com.zxzx74147.modules.info.GetInfoService;
import com.zxzx74147.modules.info.WriteInfoService;
import com.zxzx74147.modules.utils.*;
import com.zxzx74147.utils.ZXJsonUtil;

import java.sql.*;

/**
 * Created by zhengxin on 2016/12/19.
 */

public class Main {
    public static Object mWaitObj = new Object();
    private static final String SQL_CHECK = "select id from item_dbd order by id  limit 1";

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new ExitHandler());

        ZXHttpClient.setHook(new DBDHttpHook());

//        AuctionTask task2 = new AuctionTask(14216993,1101);
//        try{
//            synchronized (mWaitObj) {
//                mWaitObj.wait();
//            }
//            synchronized (mWaitObj) {
//                mWaitObj.wait();
//            }
//            synchronized (mWaitObj) {
//                mWaitObj.wait();
//            }
//            return;
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        DBService.sharedInstance().init();
        if (false) {
            Connection mConnection = DBService.sharedInstance().getConnection();
            Statement mStatement = null;
            try {
                mStatement = mConnection.createStatement();

                System.out.println("Searching:");
                long start= System.currentTimeMillis();
                ResultSet rs = mStatement.executeQuery("SELECT * FROM test_dbd WHERE MATCH('lego') AND jd_price>1800 limit 30000");

                System.out.println("time:"+(System.currentTimeMillis()-start));
                int count = 0;
                while (rs.next()) {
                    count++;
//                    for(int i=0;i<num;i++) {
//                        rs.getMetaData().getColumnName(i);
//                    }
                    int item_count = rs.getMetaData().getColumnCount();
                    System.out.print(""+ count+"|"+item_count+"|"+rs.getInt(1)+"|"+rs.getInt(2));
                    System.out.println();
                }
                System.out.println("count:"+count);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            int mStart = 0;
            Connection mConn = DBService.sharedInstance().getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet rs = null;
            try {
                preparedStatement = mConn.prepareStatement(SQL_CHECK);
                rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    mStart = rs.getInt("id");
                    mStart += 50;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                CloseUtil.close(mConn);
            }

            GetInfoService.start();
            WriteInfoService.start();
            GetInfoService.getExecutor().setOutputExecutor(WriteInfoService.getExecutor());
            for (int i = mStart; i > 9816034; i--) {
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

    }

    private static class ExitHandler extends Thread {

        public void run() {
            System.out.println("Set exit");
            DBService.sharedInstance().close();
        }
    }
}
