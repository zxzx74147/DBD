package com.zxzx74147.modules_dbd.api;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.zxzx74147.devlib.api.ApiHandler;
import com.zxzx74147.devlib.utils.UrlUtil;
import com.zxzx74147.modules_dbd.info.GetInfoService;
import com.zxzx74147.utils.ZXJsonUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * Created by zhengxin on 2017/6/6.
 */

public class DBDApiHandler extends ApiHandler {
    public static String API_ACTION_CHECK = "/action/check";

    @Override
    protected void initTable() {
        mTables.put(API_ACTION_CHECK, createHander(new IDealRequest() {
            @Override
            public String dealRequest(String url, Map<String, String> params) {
                String action_id = params.get("action_id");
                if(action_id!=null){
                    try {
                        GetInfoService.getExecutor().addInput(Integer.valueOf(action_id));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return ZXJsonUtil.toJsonString(new ErrorData());
            }
        }));
    }

    interface IDealRequest {
        public String dealRequest(String url, Map<String, String> params);
    }

    public static HttpHandler createHander(final IDealRequest iDealReqeust) {
        HttpHandler handler = new HttpHandler() {
            @Override
            public void handle(HttpExchange httpExchange) throws IOException {
                Map<String, String> params = UrlUtil.queryToMap(httpExchange.getRequestURI().toString());
                String rsp = iDealReqeust.dealRequest(httpExchange.getRequestURI().toString(), params);
                httpExchange.sendResponseHeaders(300, rsp.length());
                OutputStream os = httpExchange.getResponseBody();
                os.write(rsp.getBytes());
                os.close();
            }
        };
        return handler;
    }
}
