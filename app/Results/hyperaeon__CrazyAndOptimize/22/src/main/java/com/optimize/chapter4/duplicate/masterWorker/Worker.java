package com.optimize.chapter4.duplicate.masterWorker;
 import java.util.Map;
import java.util.Queue;
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
        Object result = handle(input);
        resultMap.put(Integer.toString(result.hashCode()), result);
    }
}


}