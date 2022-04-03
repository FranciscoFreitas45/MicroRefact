package com.optimize.chapter4;
 import java.util.concurrent.Callable;
public class RealData implements Data,Callable<String>{

 protected  String result;

 private  String para;

public RealData(String para) {
    this.para = para;
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < 10; i++) {
        sb.append(para);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
    }
    result = sb.toString();
}
@Override
public String call(){
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < 10; i++) {
        sb.append(para);
        try {
            Thread.sleep(100);
            ;
        } catch (InterruptedException e) {
        }
    }
    return sb.toString();
}


@Override
public String getResult(){
    return result;
}


}