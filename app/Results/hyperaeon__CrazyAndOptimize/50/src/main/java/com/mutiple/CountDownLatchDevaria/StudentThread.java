package com.mutiple.CountDownLatchDevaria;
 import java.util.concurrent.CountDownLatch;
public class StudentThread extends Thread{


@Override
public void run(){
    System.out.println("Arrived in class ...");
    countDownLatch.countDown();
}


}