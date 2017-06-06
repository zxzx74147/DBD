package com.zxzx74147.modules_qiushi.dao;

import java.util.List;

/**
 * Created by zhengxin on 2017/1/6.
 */
public class BaseDao<T> {

    private String TABLE = "item";

    public int[] addOrUpdateBeans(List<T> beans) {
        return null;
    }

    public int addBean(T bean) {
        return 0;
    }
}
