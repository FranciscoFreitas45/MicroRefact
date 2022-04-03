package com.crazy.chapter16.triple;
 public class A {


public void last(){
    System.out.println(Thread.currentThread().getName() + "进入A的last方法");
}


public void foo(B b){
    System.out.println(Thread.currentThread().getName() + "进入A的foo");
    try {
        Thread.sleep(200);
    } catch (Exception e) {
        e.printStackTrace();
    }
    System.out.println(Thread.currentThread().getName() + "企图调用B的last");
    b.last();
}


}