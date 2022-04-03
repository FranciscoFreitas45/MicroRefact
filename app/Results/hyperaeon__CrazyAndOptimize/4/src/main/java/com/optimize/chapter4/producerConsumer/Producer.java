package com.optimize.chapter4.producerConsumer;
 import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
public class Producer implements Runnable{

 private  boolean isRunning;

 private  BlockingQueue<PCData> queue;

 private  AtomicInteger count;

 private  int SLEEPTIME;

public Producer(BlockingQueue<PCData> queue) {
    this.queue = queue;
}
public void stop(){
    isRunning = false;
}


@Override
public void run(){
    PCData data = null;
    Random r = new Random();
    System.out.println("start producer id:" + Thread.currentThread().getId());
    try {
        while (isRunning) {
            Thread.sleep(r.nextInt(SLEEPTIME));
            data = new PCData(count.incrementAndGet());
            System.out.println(data + " is put int queue");
            if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
                System.err.println("failed to put data: " + data);
            }
        }
    } catch (InterruptedException e) {
        e.printStackTrace();
        Thread.currentThread().interrupt();
    }
}


}