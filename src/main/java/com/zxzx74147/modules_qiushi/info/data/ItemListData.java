package com.zxzx74147.modules_qiushi.info.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhengxin on 2017/1/6.
 */
public class ItemListData implements Serializable{

    public int count;
    public int total;
    public int page;
    public int err;
    public List<ItemData> items;

}
