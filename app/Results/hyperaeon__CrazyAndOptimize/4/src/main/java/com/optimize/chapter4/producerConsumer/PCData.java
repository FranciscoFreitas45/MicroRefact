package com.optimize.chapter4.producerConsumer;
 public class PCData {

 private  int initData;

public PCData(int initData) {
    this.initData = initData;
}public PCData(String d) {
    this.initData = Integer.valueOf(d);
}
public int getInitData(){
    return initData;
}


@Override
public String toString(){
    return "data:" + initData;
}


}