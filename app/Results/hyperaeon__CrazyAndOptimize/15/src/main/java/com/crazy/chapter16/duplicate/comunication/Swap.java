package com.crazy.chapter16.duplicate.comunication;
 public class Swap {

 private  boolean flag;


public void printA(int i){
    try {
        if (flag) {
            wait();
        } else {
            System.out.println("i:" + i + " -----A-----");
            flag = true;
            notifyAll();
        }
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}


public void printB(int i){
    try {
        if (!flag) {
            wait();
        } else {
            System.out.println("j:" + i + " -----B-----");
            flag = false;
            notifyAll();
        }
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}


}