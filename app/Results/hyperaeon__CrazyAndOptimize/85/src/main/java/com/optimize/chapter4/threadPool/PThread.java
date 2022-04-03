package com.optimize.chapter4.threadPool;
 public class PThread extends Thread{

 private  ThreadPool pool;

 private  Runnable target;

 private  boolean isShutDown;

 private  boolean isIdle;

public PThread(Runnable target, String name, ThreadPool pool) {
    super(name);
    this.pool = pool;
    this.target = target;
}
public Runnable getTarget(){
    return target;
}


public void setTarget(Runnable newTarget){
    target = newTarget;
    notifyAll();
}


public boolean isIdle(){
    return isIdle;
}


public void run(){
    while (!isShutDown) {
        isIdle = false;
        if (target != null) {
            target.run();
        }
        isIdle = true;
        try {
            pool.repool(this);
            synchronized (this) {
                wait();
            }
        } catch (InterruptedException e) {
        }
        isIdle = false;
    }
}


public void shutDown(){
    isShutDown = true;
    notifyAll();
}


}