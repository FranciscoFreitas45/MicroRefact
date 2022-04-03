package com.optimize.chapter4.triple.masterworker;
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
public void submit(Object job){
    workQueue.add(job);
}


public void execute(){
    for (Map.Entry<String, Thread> entry : threadMap.entrySet()) {
        entry.getValue().start();
    }
}


public boolean isComplete(){
    for (Map.Entry<String, Thread> entry : threadMap.entrySet()) {
        if (entry.getValue().getState() != Thread.State.TERMINATED) {
            return false;
        }
    }
    return true;
}


}