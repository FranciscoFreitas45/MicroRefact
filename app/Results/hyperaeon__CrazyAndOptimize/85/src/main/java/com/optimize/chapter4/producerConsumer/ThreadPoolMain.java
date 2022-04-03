package com.optimize.chapter4.producerConsumer;
 import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.optimize.chapter4.threadPool.ThreadPool;
public class ThreadPoolMain {

 private  int COUNT;


public void main(String[] args){
    long start = System.currentTimeMillis();
    for (int i = 0; i < COUNT; i++) {
        new Thread(new MyThread("testNoThreadPool " + Integer.toString(i))).start();
    }
    System.out.println("No thread pool spend " + (System.currentTimeMillis() - start) + "ms");
    start = System.currentTimeMillis();
    for (int i = 0; i < COUNT; i++) {
        ThreadPool.getInstance().start(new MyThread("testThreadPool " + Integer.toString(i)));
    }
    System.out.println("Thread pool spend " + (System.currentTimeMillis() - start) + "ms");
    ExecutorService exe = Executors.newCachedThreadPool();
    for (int i = 0; i < COUNT; i++) {
        exe.execute(new MyThread("testJDKThreadPool " + Integer.toString(i)));
    }
}


}