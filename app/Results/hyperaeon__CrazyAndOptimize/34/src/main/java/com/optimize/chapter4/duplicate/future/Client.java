package com.optimize.chapter4.duplicate.future;
 public class Client {


public Data request(String request){
    final FutureData future = new FutureData();
    new Thread() {

        public void run() {
            RealData realData = new RealData(request);
            future.setRealData(realData);
        }
    }.start();
    return future;
}


public void run(){
    RealData realData = new RealData(request);
    future.setRealData(realData);
}


}