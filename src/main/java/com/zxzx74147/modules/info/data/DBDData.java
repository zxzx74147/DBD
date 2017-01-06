package com.zxzx74147.modules.info.data;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by zhengxin on 2016/12/21.
 */
public class DBDData implements Serializable{
    public int id;
    public long jd_item_id;
    public String item_abs;
    public int time;
    public float deal_price;
    public String deal_user;
    public float jd_price;
    public int status;
    public String pack_status;

    public void insertIntoDB(PreparedStatement ps) {
        try {
            ps.setInt(1, id);
            ps.setLong(2, jd_item_id);
            ps.setString(3, item_abs==null? "":item_abs);
            ps.setInt(4, time);
            ps.setFloat(5, deal_price);
            ps.setString(6, deal_user==null? "":deal_user);
            ps.setFloat(7, jd_price);
            ps.setInt(8, status);
            ps.setString(9, pack_status==null? "":pack_status);
            ps.addBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
