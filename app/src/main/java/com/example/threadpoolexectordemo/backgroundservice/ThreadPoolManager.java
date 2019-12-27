package com.example.threadpoolexectordemo.backgroundservice;

import android.os.Message;

import com.example.threadpoolexectordemo.UiThreadCallback;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolManager {
    private static final int KEEP_ALIVE_TIME = 30;
    /**
     * Number of processors available. For more information on this you can check {@link android.os.AsyncTask}
     * internal thread pool executor.
     */
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    // We want at least 2 threads and at most 4 threads in the core pool,
    // preferring to have 1 less than the CPU count to avoid saturating
    // the CPU with background work
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    /**
     * Max thread pool size to execute threads
     */
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static ThreadPoolManager instance = null;
    private static int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    private final ExecutorService mExecutorService;
    //    private final BlockingQueue<Runnable> mTaskQueue;
    private List<Future> mRunningTaskList;
    private WeakReference<UiThreadCallback> uiThreadCallbackWeakReference;

    private ThreadPoolManager() {
        mRunningTaskList = new ArrayList<>();
        mExecutorService = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
    }


    public static ThreadPoolManager getInstance() {
        if (instance == null)
            return instance = new ThreadPoolManager();
        else
            return instance;
    }

    public void addCallable(Callable callable) {
        Future future = mExecutorService.submit(callable);
        mRunningTaskList.add(future);
    }

    public void setUiThreadCallBack(UiThreadCallback uiThreadCallBack) {
        uiThreadCallbackWeakReference = new WeakReference<UiThreadCallback>(uiThreadCallBack);
    }

    public void sendMsgToUiThread(Message message) {
        if (uiThreadCallbackWeakReference != null && uiThreadCallbackWeakReference.get() != null)
            uiThreadCallbackWeakReference.get().publishToUiThread(message);
        for (Future future : mRunningTaskList) {
            if (future.isDone())
                future.cancel(true);
        }
    }

    public void sendProgressToUiThread(Message message) {
        if (uiThreadCallbackWeakReference != null && uiThreadCallbackWeakReference.get() != null)
            uiThreadCallbackWeakReference.get().updateProgress(message);
    }

}
