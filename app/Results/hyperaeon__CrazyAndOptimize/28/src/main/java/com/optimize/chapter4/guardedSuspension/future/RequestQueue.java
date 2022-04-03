package com.optimize.chapter4.guardedSuspension.future;
 import java.util.LinkedList;
public class RequestQueue {

 private  LinkedList<Request> queue;


public void addRequest(Request request){
    queue.add(request);
    notifyAll();
}


public Request getRequest(){
    while (queue.size() == 0) {
        try {
            wait();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
    return (Request) queue.remove();
}


}