package com.optimize.chapter4.duplicate.masterWorker;
 import java.util.Map;
import java.util.Set;
public class Main {


public void main(String[] args){
    long start = System.currentTimeMillis();
    Master m = new Master(new PlusWorker(), 5);
    for (int i = 1; i <= 100; i++) {
        m.submit(i);
    }
    m.execute();
    int total = 0;
    Map<String, Object> resultMap = m.getResultMap();
    while (resultMap.size() > 0 || !m.isComplete()) {
        Set<String> keys = resultMap.keySet();
        for (String k : keys) {
            Integer i = null;
            if (k != null) {
                i = (Integer) resultMap.get(k);
            }
            if (i != null) {
                total += i;
            }
            if (k != null) {
                resultMap.remove(k);
            }
        }
    }
    System.out.println("Total waste: " + (System.currentTimeMillis() - start) + "ms");
    System.out.println(total);
}


}