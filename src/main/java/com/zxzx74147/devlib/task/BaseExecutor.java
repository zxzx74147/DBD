package com.zxzx74147.devlib.task;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhengxin on 15/12/3.
 */
public abstract class BaseExecutor<I, O> {
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
                            }
                        } else {
                            inputQueue.add(input);
                        }
                        if(inputQueue.size()>=BLOCK_NUM){
                            break;
                        }
                    }
                    List<O> output = process(inputQueue);
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
