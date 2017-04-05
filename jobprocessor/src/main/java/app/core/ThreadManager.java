package app.core;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by shivoam.malhotra on 05/04/17.
 */

/**
 * Class to manager thread pool and thread operations
 */
public class ThreadManager {

    private static ThreadPoolExecutor threadPoolExecutor = null;

    /**
     * Initializes the executor service
     * @param initialThreadCount
     */
    public static void init(int initialThreadCount){
        threadPoolExecutor = (ThreadPoolExecutor) Executors.
                newFixedThreadPool(initialThreadCount);
    }

    /**
     * Changes the thread pool size.
     * Note : It may happen that the threadpoolsize is less than the number of
     * active threads after this function has run. According to javaodocs, in
     * such a case, excess threads are terminated if they have been idle for
     * more than keepAliveTime
     *
     * @param threadCount
     */
    public static void changeThreadCount(int threadCount)
            throws RuntimeException{
        if(threadPoolExecutor == null){
            throw new RuntimeException("ThreadManager has not been initialized");
        }
        threadPoolExecutor.setCorePoolSize(threadCount);
        threadPoolExecutor.setMaximumPoolSize(threadCount);
    }

    public static int getTotalThreadCount() {
        return threadPoolExecutor.getMaximumPoolSize();
    }

    public static int getActiveThreadCount() {
        return threadPoolExecutor.getActiveCount();
    }

    /**
     * Submits a callable object to executorService
     * @param callable
     */
    public static Future submitTask(Callable callable){
        return threadPoolExecutor.submit(callable);
    }

}
