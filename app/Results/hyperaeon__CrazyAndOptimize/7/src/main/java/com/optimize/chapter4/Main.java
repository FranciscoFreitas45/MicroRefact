package com.optimize.chapter4;
 import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
public class Main {


public void main(String[] args){
    Client client = new Client();
    Data data = client.request("name");
    System.out.println("�������");
    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
    }
    System.out.println("���� = " + data.getResult());
    FutureTask<String> future = new FutureTask<String>(new RealData("a"));
    ExecutorService executor = Executors.newFixedThreadPool(1);
    executor.submit(future);
    System.out.println("�������");
    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
    }
    System.out.println("���� = " + future.get());
}


}