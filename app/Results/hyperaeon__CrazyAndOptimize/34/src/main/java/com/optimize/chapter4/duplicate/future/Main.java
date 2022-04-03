package com.optimize.chapter4.duplicate.future;
 public class Main {


public void main(String[] args){
    Client client = new Client();
    Data data = client.request("name");
    System.out.println("数据完成");
    try {
        Thread.sleep(2000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    System.out.println("数据 = " + data.getResult());
}


}