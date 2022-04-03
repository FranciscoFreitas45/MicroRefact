package com.mutiple;
 import java.util.concurrent.CountDownLatch;
public class CountDownLatchDevaria {

 private  CountDownLatch countDownLatch;


@Override
public void run(){
    System.out.println("Arrived in class ...");
    countDownLatch.countDown();
}


public void main(String[] args){
    new TeacherThread().start();
    for (int i = 0; i < countDownLatch.getCount(); i++) {
        new StudentThread().start();
    }
}


}