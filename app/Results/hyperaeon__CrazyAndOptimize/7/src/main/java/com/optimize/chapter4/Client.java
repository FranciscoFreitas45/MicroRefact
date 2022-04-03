package com.optimize.chapter4;
 public class Client {


public Data request(String queryStr){
    final FutureData future = new FutureData();
    new Thread() {

        public void run() {
            RealData realData = new RealData(queryStr);
            future.setRealData(realData);
        }
    }.start();
    return future;
}


public void run(){
    RealData realData = new RealData(queryStr);
    future.setRealData(realData);
}


}