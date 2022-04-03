package com.optimize.chapter4.threadPool;
 import java.util.List;
import java.util.Vector;
public class ThreadPool {

 private  ThreadPool instance;

 private  List<PThread> idleThreads;

 private  int threadCounter;

 private  boolean isShutDown;

private ThreadPool() {
    this.idleThreads = new Vector<>(5);
    threadCounter = 0;
}
public int getCreatedThreadsCount(){
    return threadCounter;
}


public void repool(PThread repoolingThread){
    if (!isShutDown) {
        idleThreads.add(repoolingThread);
    } else {
        repoolingThread.shutDown();
    }
}


public void start(Runnable target){
    PThread thread = null;
    if (idleThreads.size() > 0) {
        int lastIndex = idleThreads.size() - 1;
        thread = idleThreads.get(lastIndex);
        idleThreads.remove(lastIndex);
        thread.setTarget(target);
    } else {
        threadCounter++;
        thread = new PThread(target, "PThread#" + threadCounter, this);
        thread.start();
    }
}


public ThreadPool getInstance(){
    if (instance == null) {
        instance = new ThreadPool();
    }
    return instance;
}


public void shutdown(){
    isShutDown = true;
    for (int threadIndex = 0; threadIndex < idleThreads.size(); threadIndex++) {
        PThread p = idleThreads.get(threadIndex);
        p.shutDown();
    }
}


}