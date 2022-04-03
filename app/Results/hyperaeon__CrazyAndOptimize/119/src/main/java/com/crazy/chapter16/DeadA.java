package com.crazy.chapter16;
 public class DeadA {


public void last(){
    System.out.println("A��last����");
}


public void foo(DeadB b){
    System.out.println("��ǰ�߳�����" + Thread.currentThread().getName() + " ����A��foo����");
    try {
        Thread.sleep(100);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    System.out.println("����B��last����");
    b.last();
}


}