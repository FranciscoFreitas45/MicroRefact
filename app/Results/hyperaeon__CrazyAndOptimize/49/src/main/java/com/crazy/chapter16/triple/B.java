package com.crazy.chapter16.triple;
 public class B {


public void bar(A a){
    System.out.println(Thread.currentThread().getName() + "进入B的bar");
    try {
        Thread.sleep(200);
    } catch (Exception e) {
        e.printStackTrace();
    }
    System.out.println(Thread.currentThread().getName() + "企图调用a的last");
    a.last();
}


public void last(){
    System.out.println(Thread.currentThread().getName() + "进入B的last方法");
}


}