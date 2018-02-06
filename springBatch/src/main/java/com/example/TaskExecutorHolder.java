package com.example;

import org.springframework.core.task.TaskExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Desc ：TaskExecutorHolder
 * Created by JHAO on 2018/2/2.
 */
public class TaskExecutorHolder implements TaskExecutor {

    @Override
    public void execute(Runnable runnable) {
        ExecutorService taskExecutor = Executors.newFixedThreadPool(20);
//        ExecutorService taskExecutor = new ThreadPoolExecutor(10,20,3000, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>());
        taskExecutor.submit(runnable);
    }
}
