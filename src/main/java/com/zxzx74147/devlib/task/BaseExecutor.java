package com.zxzx74147.devlib.task;

import com.zxzx74147.devlib.log.ZXLog;
import com.zxzx74147.modules_dbd.info.data.IData;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by zhengxin on 15/12/3.
 */
public abstract class BaseExecutor<I, O> {
    protected String TAG = this.getClass().getName();
    public interface OutputListener<T> {
        public void onOutput(T output);
    }

    private ThreadPoolExecutor mExecutor;
    private LinkedBlockingQueue<Runnable> mThreadPoolQueue;
    private LinkedBlockingQueue<I> mDataQueue;
    private BaseExecutor<O, ?> mOutputExecutor;
    private OutputListener<O> mOutputListener;
    public volatile boolean mHasDone = false;
    private String mExcutorName;
    protected int BLOCK_NUM = 50;
    private static HashMap<String,AtomicInteger> mLastCountMap = new HashMap<>();
    private static HashMap<String,AtomicLong> mLastTimeMap = new HashMap<>();
    private AtomicInteger mLastCount = null;
    private AtomicLong mLastTime = null;

    public BaseExecutor(int threadNum, int bufferNum) {
        this(threadNum, bufferNum, null);
    }

    public BaseExecutor(int threadNum, int bufferNum, String excutorName) {
        mExcutorName = excutorName;
        mDataQueue = new LinkedBlockingQueue<I>(bufferNum);
        mThreadPoolQueue = new LinkedBlockingQueue<Runnable>(threadNum);
        mExecutor = new ThreadPoolExecutor(threadNum, threadNum * 2, Integer.MAX_VALUE, TimeUnit.DAYS, mThreadPoolQueue);
        for (int i = 0; i < threadNum; i++) {
            mExecutor.execute(new InnerRunnable(i));
        }
        synchronized (mLastCountMap) {
            if (!mLastCountMap.containsKey(TAG)) {
                mLastCountMap.put(TAG, new AtomicInteger());
            }
            mLastCount = mLastCountMap.get(TAG);
        }
        synchronized(mLastTimeMap) {
            if (!mLastTimeMap.containsKey(TAG)) {
                mLastTimeMap.put(TAG, new AtomicLong());
            }
            mLastTime = mLastTimeMap.get(TAG);
        }
    }

    public void shutdown() {
        mExecutor.shutdown();
    }

    public void addInput(I input) throws InterruptedException {
        mDataQueue.put(input);
    }

    public void setOutputExecutor(BaseExecutor<O, ?> outputExecutor) {
        mOutputExecutor = outputExecutor;
    }

    public void setOutputExecutor(OutputListener<O> outputExecutor) {
        mOutputListener = outputExecutor;
    }

    private class InnerRunnable implements Runnable {

        protected int mId;

        public InnerRunnable(int id) {
            mId = id;
        }

        @Override
        public void run() {
            Thread.currentThread().setName(mExcutorName + "_" + mId);
            try {
                while (true) {
                    List<I> inputQueue = new LinkedList<>();
                    while (true) {
                        I input = mDataQueue.poll();
                        if (input == null) {
                            if (inputQueue.size() != 0 || mHasDone) {
                                break;
                            } else {
                                input = mDataQueue.take();
                                inputQueue.add(input);
                                mLastCount.addAndGet(1);
                            }
                        } else {
                            inputQueue.add(input);
                            mLastCount.addAndGet(1);
                        }
                        if(inputQueue.size()>=BLOCK_NUM){
                            break;
                        }
                    }
                    List<O> output = process(inputQueue);

                    long now = System.currentTimeMillis();
                    if(now/1000!=mLastTime.get()/1000){
                        int queueLen = mDataQueue.size();
                        StringBuffer sb = new StringBuffer(80);

                        sb.append((mLastCount.get()*1000f/(now-mLastTime.get()))+" items/second  ");


                        if(inputQueue!=null&&inputQueue.size()>0) {
                            if(inputQueue.get(0) instanceof IData){
                                sb.append("current item: " + ((IData) inputQueue.get(0)).getID() );
                            }else {
                                sb.append("current item: " + inputQueue.get(0).toString());
                            }
                        }
                        sb.append("  quene len:"+queueLen);
                        ZXLog.i(TAG,sb.toString());
                        mLastTime.set(now);
                        mLastCount.set(0);
                    }

                    if (output == null) {
                        continue;
                    }
                    if (mOutputExecutor != null) {
                        for (O out : output) {
                            mOutputExecutor.addInput(out);
                        }
                    }
                    if (mOutputListener != null) {
                        for (O out : output) {
                            mOutputListener.onOutput(out);
                        }

                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public abstract List<O> process(List<I> input);
}
