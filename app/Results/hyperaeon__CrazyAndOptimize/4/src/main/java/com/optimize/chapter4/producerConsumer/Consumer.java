package com.optimize.chapter4.producerConsumer;
 import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
public class Consumer implements Runnable{

 private  BlockingQueue<PCData> queue;

 private  int SLEEPTIME;

public Consumer(BlockingQueue<PCData> queue) {
    this.queue = queue;
}
@Override
public void run(){
    System.out.println("start consumer id=" + Thread.currentThread().getId());
    Random r = new Random();
    try {
        while (true) {
            PCData data = queue.take();
            if (data != null) {
                int re = data.getInitData() << 1;
                System.out.println(MessageFormat.format("{0}*2={1}", data.getInitData(), re));
                Thread.sleep(r.nextInt(SLEEPTIME));
            }
        }
    } catch (InterruptedException e) {
        e.printStackTrace();
        Thread.currentThread().interrupt();
    }
}


}