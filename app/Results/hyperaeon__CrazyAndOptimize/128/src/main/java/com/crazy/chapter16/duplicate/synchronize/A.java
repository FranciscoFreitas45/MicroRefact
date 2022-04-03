package com.crazy.chapter16.duplicate.synchronize;
 public class A {


public void last(){
    System.out.println("������A���last�����ڲ�");
}


public void foo(B b){
    System.out.println("��ǰ�̣߳� " + Thread.currentThread().getName() + " ������Aʵ����foo����");
    try {
        Thread.sleep(200);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    System.out.println("��ǰ�̣߳� " + Thread.currentThread().getName() + " ������Bʵ����last����");
    b.last();
}


}