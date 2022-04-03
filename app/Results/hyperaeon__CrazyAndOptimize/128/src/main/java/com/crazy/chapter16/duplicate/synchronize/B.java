package com.crazy.chapter16.duplicate.synchronize;
 public class B {


public void bar(A a){
    System.out.println("��ǰ�̣߳� " + Thread.currentThread().getName() + " ������Bʵ����bar����");
    try {
        Thread.sleep(200);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    System.out.println("��ǰ�̣߳� " + Thread.currentThread().getName() + " ������Aʵ����last����");
    a.last();
}


public void last(){
    System.out.println("������B���last�����ڲ�");
}


}