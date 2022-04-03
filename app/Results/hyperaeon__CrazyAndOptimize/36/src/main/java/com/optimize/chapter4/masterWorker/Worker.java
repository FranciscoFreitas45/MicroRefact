package com.optimize.chapter4.masterWorker;
 import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
public class Worker implements Runnable{

 protected  Queue<Object> workQueue;

 protected  Map<String,Object> resultMap;


public void setResultMap(Map<String,Object> resultMap){
    this.resultMap = resultMap;
}


public void setWorkQueue(Queue<Object> workQueue){
    this.workQueue = workQueue;
}


public Object handle(Object input){
    return input;
}


@Override
public void run(){
    while (true) {
        Object input = workQueue.poll();
        if (input == null) {
            break;
        }
        Object handle = handle(input);
        resultMap.put(Integer.toString(input.hashCode()), handle);
    }
}


}