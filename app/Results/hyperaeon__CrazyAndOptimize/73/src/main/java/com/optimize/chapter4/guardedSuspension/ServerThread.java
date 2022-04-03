package com.optimize.chapter4.guardedSuspension;
 public class ServerThread extends Thread{

 private  RequestQueue requestQueue;

public ServerThread(RequestQueue requestQueue, String name) {
    super(name);
    this.requestQueue = requestQueue;
}
public void run(){
    while (true) {
        final Request request = requestQueue.getRequest();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " handles " + request);
    }
}


}