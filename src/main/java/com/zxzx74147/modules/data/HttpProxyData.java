package com.zxzx74147.modules.data;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * Created by zhengxin on 2017/1/16.
 */
public class HttpProxyData {
    public String ip;
    public int port;
    //TODO
    public String user;
    public String pass;
    private Proxy proxy = null;

    public Proxy getProxy() {
        if (proxy == null) {
            InetSocketAddress addr = new InetSocketAddress(ip, port);
            proxy = new Proxy(Proxy.Type.HTTP, addr);
        }
        return proxy;
    }
}
