package com.zxzx74147.modules.base;

import com.zxzx74147.devlib.task.BaseExecutor;
import com.zxzx74147.modules.data.HttpProxyData;
import com.zxzx74147.modules_qiushi.info.data.ItemData;

import java.net.Proxy;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by zhengxin on 2017/1/16.
 */
public abstract class GetInfoBaseExecutor<I, O> extends BaseExecutor<I, O> {

    protected List<HttpProxyData> mProxys = null;
    private  int mIndex = 0;


    public GetInfoBaseExecutor(int threadNum, int bufferNum) {
        super(threadNum, bufferNum);
    }

    public synchronized void setProxy(List<HttpProxyData> proxyDatas){
        mProxys = proxyDatas;
    }

    @Override
    public List<O> process(List<I> input) {
        return null;
    }

    private synchronized Proxy getRandomProxy(){
        if(mProxys==null||mProxys.size()==0){
            return null;
        }
        mIndex++;
        mIndex%=mProxys.size();
        return mProxys.get(mIndex).getProxy();
    }
}
