package com.crazy.chapter16;
 public class DeadLock implements Runnable{

 private DeadA a;

 private DeadB b;


public void init(){
    Thread.currentThread().setName("���߳�");
    a.foo(b);
    System.out.println("�������߳�֮��");
}


@Override
public void run(){
    Thread.currentThread().setName("���߳�");
    b.bar(a);
    System.out.println("���븱�߳�֮��");
}


public void main(String[] args){
    DeadLock l = new DeadLock();
    new Thread(l).start();
    l.init();
}


}