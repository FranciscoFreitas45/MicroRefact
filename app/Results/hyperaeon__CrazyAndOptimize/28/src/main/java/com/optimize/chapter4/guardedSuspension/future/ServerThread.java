package com.optimize.chapter4.guardedSuspension.future;
 import com.optimize.chapter4.guardedSuspension.RealData;
public class ServerThread extends Thread{

 private  RequestQueue requestQueue;

public ServerThread(RequestQueue requestQueue, String name) {
    super(name);
    this.requestQueue = requestQueue;
}
public void run(){
    while (true) {
        final Request request = requestQueue.getRequest();
        final FutureData future = (FutureData) request.getResponse();
        RealData realData = new RealData(request.getName());
        future.setRealData(realData);
        System.out.println(Thread.currentThread().getName() + " handles " + request);
    }
}


}