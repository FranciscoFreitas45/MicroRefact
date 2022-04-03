package com.optimize.chapter4.duplicate.future;
 public class RealData implements Data{

 protected  String result;

public RealData(String param) {
    StringBuffer buffer = new StringBuffer();
    for (int i = 0; i < 10; i++) {
        buffer.append(param);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    result = buffer.toString();
}
public String getResult(){
    return result;
}


}