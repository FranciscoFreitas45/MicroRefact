package com.optimize.chapter4.guardedSuspension;
 public class ClientThread extends Thread{

 private  RequestQueue requestQueue;

public ClientThread(RequestQueue requestQueue, String name) {
    super(name);
    this.requestQueue = requestQueue;
}
public void run(){
    for (int i = 0; i < 10; i++) {
        Request request = new Request("Request ID: " + i + " Thread_Name: " + Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getName() + " requests " + request);
        requestQueue.addRequest(request);
        try {
            Thread.sleep(10);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        System.out.println("ClientThread Name is:" + Thread.currentThread().getName());
    }
    System.out.println(Thread.currentThread().getName() + " request end");
}


}