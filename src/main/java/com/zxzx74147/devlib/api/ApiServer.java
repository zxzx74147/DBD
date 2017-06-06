package com.zxzx74147.devlib.api;

/**
 * Created by zhengxin on 2017/6/6.
 */
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;

public class ApiServer {
    private static HttpServer server = null;
    public static boolean init(ApiConfig config,ApiHandler handler ){

        try {
            server = HttpServer.create(new InetSocketAddress(config.PORT), 0);
            for (Map.Entry<String,HttpHandler> entry: handler.getApiTable().entrySet()) {
                server.createContext(entry.getKey(),entry.getValue());
            }
            server.setExecutor(null); // creates a default executor
            server.start();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean destroy(){
        server.stop(0);
        return true;
    }
}
