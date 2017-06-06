package com.zxzx74147.modules_qiushi.dao;

import com.zxzx74147.modules_qiushi.info.data.ItemData;

/**
 * Created by zhengxin on 2017/1/6.
 */
public class ItemDao  extends BaseDao<ItemData> {
    private static final String INSERT_SQL = "insert into item(id,jd_item_id,item_abs,time,deal_price,deal_user,jd_price,status,pack_status) values(?,?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE " +
            "id=VALUES(id), deal_price=VALUES(deal_price),item_abs=VALUES(item_abs),pack_status=VALUES(pack_status), deal_user=VALUES(deal_user), status=VALUES(status)";



}
