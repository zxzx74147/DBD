package com.zxzx74147.devlib.api;

import com.sun.net.httpserver.HttpHandler;

import java.util.HashMap;

/**
 * Created by zhengxin on 2017/6/6.
 */
public  class ApiHandler {
    protected HashMap<String,HttpHandler> mTables = new HashMap<>(10);

    public HashMap<String,HttpHandler> getApiTable(){
        initTable();
        return mTables;
    }

    protected void initTable(){

    }
}
