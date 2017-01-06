package com.zxzx74147.modules.info.data;

import java.util.List;

/**
 * Created by zhengxin on 2016/12/21.
 */
public class BigPaimaiData {

    public ModelBean model;

    public static class ModelBean {
        public int paimaiId;
        public int sku;
        public Object productIntroduction;
        public Object productSpecification;
        public List<?> sameSku;
        public List<?> recomList;
    }
}
