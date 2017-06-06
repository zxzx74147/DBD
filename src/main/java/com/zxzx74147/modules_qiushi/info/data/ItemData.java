package com.zxzx74147.modules_qiushi.info.data;

import com.zxzx74147.utils.ZXJsonUtil;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhengxin on 2017/1/6.
 */
public class ItemData implements Serializable {

    public String format;
    public String image;
    public int published_at;
    public String tag;
    public UserData user;
    public Object image_size;
    public int id;
    public VotesData votes;
    public int created_at;
    public String content;
    public String state;
    public int comments_count;
    public boolean allow_comment;
    public int share_count;
    public String type;
    public CommentData hot_comment;
    public String high_loc;
    public String low_loc;
    public String high_url;


    public String pic_loc;
    public String pic_url;
    public String low_url;
    public int loop;
    public List<Integer> pic_size;

    public static final String INSERT_SQL = "insert into item(id,user_id,create_time,content,format,image,image_low,image_high,image_size,video,video_low,video_pic,video_pic_size,video_loop,share_count,status,commont_count,commont_hot,vote_up,vote_down,type) " +
            "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE " +
            "id=VALUES(id), user_id=VALUES(user_id),vote_up=VALUES(vote_up),vote_down=VALUES(vote_down),video_loop=VALUES(video_loop), share_count=VALUES(share_count), status=VALUES(status)";


    public void insertIntoDB(PreparedStatement ps) {
        try {
            //,,,,,,,,,,,,,,,,,,,)
            ps.setInt(1, id);//id
            ps.setInt(2, user==null? 0:user.id);//user_id
            ps.setInt(3, created_at);//create_time
            ps.setString(4, content);//content
            ps.setString(5, format);//format
            ps.setString(6, image);//image
            ps.setString(7, low_loc);//image_low
            ps.setString(8, high_loc);//image_high
            ps.setString(9, ZXJsonUtil.toJsonString(image_size));//image_size
            ps.setString(10,high_url );//video
            ps.setString(11, low_url);//video_low
            ps.setString(12, pic_loc);//video_pic
            ps.setString(13, ZXJsonUtil.toJsonString(pic_size));//video_pic_size
            ps.setInt(14, loop);//video_loop
            ps.setInt(15, share_count);//share_count
            ps.setString(16, state);//status
            ps.setInt(17, comments_count);//commont_count
            ps.setString(18, ZXJsonUtil.toJsonString(hot_comment));//commont_hot
            ps.setInt(19, votes.up);//vote_up
            ps.setInt(20, -votes.down);//vote_down
            ps.setString(21, type);//vote_down
            ps.addBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
