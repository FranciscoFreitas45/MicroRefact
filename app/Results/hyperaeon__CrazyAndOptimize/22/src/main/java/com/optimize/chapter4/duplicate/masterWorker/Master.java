package com.optimize.chapter4.duplicate.masterWorker;
 import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
public class Master {

 protected  Queue<Object> workQueue;

 protected  Map<String,Thread> threadMap;

 protected  Map<String,Object> resultMap;

public Master(Worker worker, int countWorker) {
    worker.setWorkQueue(workQueue);
    worker.setResultMap(resultMap);
    for (int i = 0; i < countWorker; i++) {
        threadMap.put(Integer.toString(i), new Thread(worker, Integer.toString(i)));
    }
}
public Map<String,Object> getResultMap(){
    return resultMap;
}


public void submit(Object obj){
    workQueue.add(obj);
}


public void execute(){
    for (Map.Entry<String, Thread> thread : threadMap.entrySet()) {
        thread.getValue().start();
    }
}


public boolean isComplete(){
    for (Map.Entry<String, Thread> thread : threadMap.entrySet()) {
        if (thread.getValue().getState() != Thread.State.TERMINATED) {
            return false;
        }
    }
    return true;
}


}