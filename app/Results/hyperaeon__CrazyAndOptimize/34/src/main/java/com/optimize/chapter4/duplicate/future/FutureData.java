package com.optimize.chapter4.duplicate.future;
 public class FutureData implements Data{

 protected  boolean isReady;

 protected  RealData realData;


@Override
public String getResult(){
    while (!isReady) {
        try {
            wait();
        } catch (InterruptedException e) {
        }
    }
    return realData.result;
}


public void setRealData(RealData realData){
    if (isReady) {
        return;
    }
    this.realData = realData;
    isReady = true;
    notifyAll();
}


}