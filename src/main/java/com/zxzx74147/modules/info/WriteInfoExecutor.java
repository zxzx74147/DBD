package com.zxzx74147.modules.info;

import com.zxzx74147.devlib.http.ZXHttpRequest;
import com.zxzx74147.devlib.http.ZXHttpResponse;
import com.zxzx74147.devlib.log.ZXLog;
import com.zxzx74147.devlib.task.BaseExecutor;
import com.zxzx74147.modules.Config;
import com.zxzx74147.modules.db.DBService;
import com.zxzx74147.modules.info.data.BidListData;
import com.zxzx74147.modules.info.data.DBDData;
import com.zxzx74147.modules.info.data.PriceData;
import com.zxzx74147.modules.utils.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * Created by zhengxin on 2016/12/21.
 */
public class WriteInfoExecutor extends BaseExecutor<DBDData, Object> {
    private static final String TAG = WriteInfoExecutor.class.getName();
    private static final String INSERT_SQL = "insert into item_dbd(id,jd_item_id,item_abs,time,deal_price,deal_user,jd_price,status,pack_status) values(?,?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE " +
                                            "id=VALUES(id), deal_price=VALUES(deal_price),item_abs=VALUES(item_abs),pack_status=VALUES(pack_status), deal_user=VALUES(deal_user), status=VALUES(status)";
    private long mLastTime = 0;
    private int mLastCount = 0;

    public WriteInfoExecutor(int threadNum, int bufferNum) {
        super(threadNum, bufferNum, "WriteInfoExecutor Thread");
        BLOCK_NUM = 1;
    }

    @Override
    public List<Object> process(List<DBDData> input) {
        List<DBDData> mCacheList = input;
        Connection mConn = DBService.sharedInstance().getConnection();
        PreparedStatement ps = null;
        try {
            if(mCacheList.size()==0){
                return null;
            }
            mLastCount+=mCacheList.size();
            long now = System.currentTimeMillis();
            if(now/1000!=mLastTime/1000){
                ZXLog.i(TAG,mLastCount+" items/second");
                ZXLog.i(TAG,"current id "+mCacheList.get(0).id);
                mLastTime = now;
                mLastCount = 0;
            }
            ps = mConn.prepareStatement(INSERT_SQL);

//            System.out.println("write_size=" + mCacheList.size());
            for (DBDData data : mCacheList) {
                data.insertIntoDB(ps);
            }
            int[] result = ps.executeBatch();

        } catch (Exception e) {
            e.printStackTrace();
            for (DBDData data : mCacheList) {
                ZXLog.e(TAG,""+data.jd_item_id);
            }
        } finally {

            try {
                if (ps != null) {
                    ps.clearParameters();
                    ps.clearBatch();
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            CloseUtil.close(mConn);
        }
        return null;
    }
}
