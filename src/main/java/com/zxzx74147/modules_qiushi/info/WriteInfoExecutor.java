package com.zxzx74147.modules_qiushi.info;

import com.zxzx74147.devlib.log.ZXLog;
import com.zxzx74147.devlib.task.BaseExecutor;
import com.zxzx74147.devlib.utils.CloseUtil;
import com.zxzx74147.modules_qiushi.db.DBService;
import com.zxzx74147.modules_qiushi.info.data.ItemData;
import com.zxzx74147.modules_qiushi.info.data.UserData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by zhengxin on 2016/12/21.
 */
public class WriteInfoExecutor extends BaseExecutor<ItemData, Object> {
    private static final String TAG = WriteInfoExecutor.class.getName();
    private static AtomicInteger mLastCount = new AtomicInteger();
    private static AtomicLong  mLastTime = new AtomicLong();

    public WriteInfoExecutor(int threadNum, int bufferNum) {
        super(threadNum, bufferNum, "WriteInfoExecutor Thread");
        BLOCK_NUM = 10;
    }

    @Override
    public List<Object> process(List<ItemData> input) {
        List<ItemData> mCacheList = input;
        Connection mConn = DBService.sharedInstance().getConnection();
        PreparedStatement ps = null;
        PreparedStatement ps_user = null;
        try {
            if(mCacheList.size()==0){
                return null;
            }
            mLastCount.getAndAdd(mCacheList.size());
            long now = System.currentTimeMillis();
            if(now/1000!=mLastTime.get()/1000){
                ZXLog.i(TAG,mLastCount+" items/second");
                ZXLog.i(TAG,"current id "+mCacheList.get(0).id);
                mLastTime.set(now);
                mLastCount.set(0);
            }
            ps = mConn.prepareStatement(ItemData.INSERT_SQL);
            ps_user = mConn.prepareStatement(UserData.INSERT_SQL);
//            System.out.println("write_size=" + mCacheList.size());
            for (ItemData data : mCacheList) {
                data.insertIntoDB(ps);
                if(data.user!=null) {
                    data.user.insertIntoDB(ps_user);
                }
            }
            int[] result = ps.executeBatch();

            for(int temp:result){
//                ZXLog.i(TAG,"result="+temp);
            }
            result = ps_user.executeBatch();
            for(int temp:result){
//                ZXLog.i(TAG,"result="+temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (ps != null) {
                    ps.clearParameters();
                    ps.clearBatch();
                    ps.close();
                }
                if (ps_user != null) {
                    ps_user.clearParameters();
                    ps_user.clearBatch();
                    ps_user.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            CloseUtil.close(mConn);
        }
        return null;
    }
}
