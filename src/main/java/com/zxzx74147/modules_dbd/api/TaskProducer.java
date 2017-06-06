package com.zxzx74147.modules_dbd.api;

import com.zxzx74147.modules_dbd.info.GetInfoExecutor;
import com.zxzx74147.modules_dbd.info.GetInfoService;

/**
 * Created by zhengxin on 2017/6/6.
 */
public class TaskProducer {
    private Thread mThread = null;
    private int mStart = 0;
    private int mEnd = 0;

    public TaskProducer(final int start,final int end){
        mStart= start;
        mEnd = end;
        if(mEnd<mStart){
            throw new IllegalArgumentException("start is bigger than end");
        }
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=mStart;i<=end&&!Thread.interrupted();i++){
                    try {
                        GetInfoService.getExecutor().addInput(i);
                    } catch (InterruptedException e) {
                        break;
                    }
                    mThread = null;
                }
            }
        });
        mThread.start();

    }

    public void cancel(){
        if(mThread!=null) {
            mThread.interrupt();
        }
    }
}
