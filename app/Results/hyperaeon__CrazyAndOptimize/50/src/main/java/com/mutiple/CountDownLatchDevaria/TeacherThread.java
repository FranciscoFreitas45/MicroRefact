package com.mutiple.CountDownLatchDevaria;
 import java.util.concurrent.CountDownLatch;
public class TeacherThread extends Thread{


@Override
public void run(){
    System.out.println("Waiting for the students, total number: " + countDownLatch.getCount());
    try {
        countDownLatch.await();
    } catch (Exception e) {
        e.printStackTrace();
    }
    System.out.println("Class begin ...");
}


}