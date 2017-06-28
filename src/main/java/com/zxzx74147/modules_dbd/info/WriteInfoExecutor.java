package com.zxzx74147.modules_dbd.info;

import com.zxzx74147.devlib.log.ZXLog;
import com.zxzx74147.devlib.task.BaseExecutor;
import com.zxzx74147.devlib.utils.CloseUtil;
import com.zxzx74147.modules_dbd.db.DBService;
import com.zxzx74147.modules_dbd.info.data.DBDData;
import redis.clients.jedis.Jedis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by zhengxin on 2016/12/21.
 */
public class WriteInfoExecutor extends BaseExecutor<DBDData, Object> {
    private static final String TAG = WriteInfoExecutor.class.getName();
    private static final String INSERT_SQL = "insert into item_dbd(id,jd_item_id,item_abs,time,deal_price,deal_user,jd_price,status,pack_status,attach,use_status) values(?,?,?,?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE " +
            "id=VALUES(id), deal_price=VALUES(deal_price),item_abs=VALUES(item_abs),pack_status=VALUES(pack_status), deal_user=VALUES(deal_user), status=VALUES(status), attach=VALUES(attach), use_status=VALUES(use_status)";
    private static final String INSERT_SQL_JD_ITEM = "insert into item_jd(id,image,abs) values(?,?,?) ON DUPLICATE KEY UPDATE " +
            "id=VALUES(id), image=VALUES(image),abs=VALUES(abs)";

//    private long mLastTime = 0;
//    private int mLastCount = 0;

    public WriteInfoExecutor(int threadNum, int bufferNum) {
        super(threadNum, bufferNum, "WriteInfoExecutor Thread");
        BLOCK_NUM = 50;
    }


    @Override
    public List<Object> process(List<DBDData> input) {
        List<DBDData> mCacheList = input;
        Connection mConn = DBService.sharedInstance().getConnection();
        PreparedStatement ps = null;
        PreparedStatement ps_jd = null;
        HashSet<Long> mIds = new HashSet<>();
        Jedis mJedis = new Jedis("localhost");
        try {
            if (mCacheList.size() == 0) {
                return null;
            }
//            mLastCount+=mCacheList.size();
//            long now = System.currentTimeMillis();
//            if(now/1000!=mLastTime/1000){
////                ZXLog.i(TAG,mLastCount+" items/second");
////                ZXLog.i(TAG,"current id "+mCacheList.get(0).id);
//                mLastTime = now;
//                mLastCount = 0;
//            }
            ps = mConn.prepareStatement(INSERT_SQL);
            ps_jd = mConn.prepareStatement(INSERT_SQL_JD_ITEM);

//            System.out.println("write_size=" + mCacheList.size());
            for (DBDData data : mCacheList) {
                data.insertIntoDB(ps);
                if (data.image != null) {
                    ps_jd.setLong(1, data.jd_item_id);
                    ps_jd.setString(2, data.image);
                    ps_jd.setString(3,data.item_abs);
                    ps_jd.addBatch();
                    mIds.add(data.jd_item_id);
                }
            }
            int[] result = ps.executeBatch();
            result = ps_jd.executeBatch();

            for (Long id : mIds) {
                mJedis.del("IA_"+id);
            }

//            ZXLog.i(TAG,result[0]+"");
        } catch (Exception e) {
            e.printStackTrace();
            for (DBDData data : mCacheList) {
                ZXLog.e(TAG, "" + data.jd_item_id);
            }
        } finally {
            CloseUtil.close(ps);
            CloseUtil.close(ps_jd);
            CloseUtil.close(mConn);
            CloseUtil.close(mJedis);
        }
        return null;
    }
}
