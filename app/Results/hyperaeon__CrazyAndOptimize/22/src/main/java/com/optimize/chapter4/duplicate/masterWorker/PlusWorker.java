package com.optimize.chapter4.duplicate.masterWorker;
 public class PlusWorker extends Worker{


public Object handle(Object input){
    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    Integer i = (Integer) input;
    return i * i * i;
}


}