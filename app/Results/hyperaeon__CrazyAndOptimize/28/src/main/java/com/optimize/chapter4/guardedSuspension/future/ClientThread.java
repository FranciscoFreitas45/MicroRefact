package com.optimize.chapter4.guardedSuspension.future;
 import java.util.ArrayList;
import java.util.List;
public class ClientThread extends Thread{

 private  RequestQueue requestQueue;

 private  List<Request> myRequest;

public ClientThread(RequestQueue requestQueue, String name) {
    super(name);
    this.requestQueue = requestQueue;
}
public void run(){
    for (int i = 0; i < 10; i++) {
        Request request = new Request("Request ID: " + i + " Thread_Name: " + Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getName() + " requests " + request);
        request.setResponse(new FutureData());
        requestQueue.addRequest(request);
        myRequest.add(request);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }
    for (Request r : myRequest) {
        System.out.println("ClientThread Name is:" + Thread.currentThread().getName() + " Response is : " + r.getResponse().getResult());
    }
}


}