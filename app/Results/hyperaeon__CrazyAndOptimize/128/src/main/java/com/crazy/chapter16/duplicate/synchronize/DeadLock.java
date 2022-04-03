package com.crazy.chapter16.duplicate.synchronize;
 public class DeadLock implements Runnable{

 private A a;

 private B b;


public void init(){
    Thread.currentThread().setName("���߳�");
    a.foo(b);
    System.out.println("���������߳�֮��");
}


@Override
public void run(){
    Thread.currentThread().setName("���߳�");
    b.bar(a);
    System.out.println("�����˸��߳�֮��");
}


public void main(String[] args){
    DeadLock dl = new DeadLock();
    new Thread(dl).start();
    dl.init();
}


}