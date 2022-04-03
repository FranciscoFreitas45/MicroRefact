package com.optimize.chapter4;
 public class FutureData implements Data{

 protected  RealData realData;

 protected  boolean isReady;


@Override
public String getResult(){
    while (!isReady) {
        try {
            wait();
        } catch (InterruptedException e) {
        }
    }
    return realData.getResult();
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