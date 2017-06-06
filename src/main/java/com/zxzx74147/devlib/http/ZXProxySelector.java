package com.zxzx74147.devlib.http;




import sun.misc.LRUCache;

import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhengxin on 2017/1/16.
 */
public class ZXProxySelector extends ProxySelector {

    private static ZXProxySelector mInstance;

    public static ZXProxySelector sharedInstance(){
        if(mInstance==null){
            mInstance = new ZXProxySelector();
        }
        return mInstance;
    }

    private LruCache<String,Proxy> mTable = new LruCache<>(300);

    private ZXProxySelector(){

    }

    public void putProxy(URI uri,Proxy proxy){
        mTable.put(uri.toString(),proxy);
    }


    @Override
    public List<Proxy> select(URI uri) {
        Proxy proxy = mTable.remove(uri.toString());
        if(proxy==null){
            return null;
        }else {
            List<Proxy> list = new LinkedList<>();
            list.add(proxy);
            return list;
        }
    }

    @Override
    public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {

    }
}
