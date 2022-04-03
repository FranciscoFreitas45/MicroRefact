package com.optimize.chapter4.concurrentDataStructure;
 import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
public class CounterPoolExecutor extends ThreadPoolExecutor{

 private  int TASK_COUNT;

 private  AtomicInteger count;

 public  long startTime;

 public  String functionName;

public CounterPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
    super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
}
public void afterExecute(Runnable r,Throwable t){
    int lo = count.addAndGet(1);
    if (lo == TASK_COUNT) {
        System.out.println(functionName + "spend time:" + (System.currentTimeMillis() - startTime));
    }
}


}