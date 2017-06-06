package com.zxzx74147.modules_qiushi.info.data;

import com.zxzx74147.utils.ZXJsonUtil;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by zhengxin on 2017/1/6.
 */
public class UserData implements Serializable {
    public int avatar_updated_at;
    public String medium;
    public String thumb;
    public int last_visited_at;
    public int created_at;
    public int updated_at;
    public String state;
    public String role;
    public String login;
    public String last_device;
    public int id;
    public String icon;



    public static final String INSERT_SQL = "INSERT INTO `db_qiushi`.`user_table` (`id`,`name`, `protrait`, `portrait_small`, `create_time`, `state`, `device`, `icon`, `last_visit`) VALUES (?,?,?,?,?,?,?,?,?)" +
            " ON DUPLICATE KEY UPDATE " +
            "protrait=VALUES(protrait), portrait_small=VALUES(portrait_small),name=VALUES(name), state=VALUES(state), device=VALUES(device), last_visit=VALUES(last_visit)";

    public void insertIntoDB(PreparedStatement ps) {
        try {

            ps.setInt(1, id);//id
            ps.setString(2, login);//user_id
            ps.setString(3, medium);//user_id
            ps.setString(4, thumb);//create_time
            ps.setInt(5, created_at);//content
            ps.setString(6, state);//format
            ps.setString(7, last_device);//image
            ps.setString(8, icon);//image_low
            ps.setInt(9, last_visited_at);//image_high
            ps.addBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
