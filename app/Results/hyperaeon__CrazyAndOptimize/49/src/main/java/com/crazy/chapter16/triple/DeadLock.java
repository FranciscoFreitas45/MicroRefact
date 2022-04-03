package com.crazy.chapter16.triple;
 public class DeadLock implements Runnable{

 private  A a;

 private  B b;

public DeadLock() {
}public DeadLock(A a, B b) {
    this.a = a;
    this.b = b;
}
public void init(){
    Thread.currentThread().setName("主线程");
    b.bar(a);
    System.out.println("进入主线程后");
}


@Override
public void run(){
    Thread.currentThread().setName("副线程");
    a.foo(b);
    System.out.println("进入副线程后");
}


public void main(String[] args){
    A a = new A();
    B b = new B();
    DeadLock deadLock = new DeadLock(a, b);
    Thread thread = new Thread(deadLock, "主线程");
    thread.start();
    deadLock.init();
}


}