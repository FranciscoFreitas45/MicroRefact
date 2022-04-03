package com.crazy.chapter16;
 public class DeadB {


public void bar(DeadA a){
    System.out.println("��ǰ�߳�����" + Thread.currentThread().getName() + "����B��bar����");
    try {
        Thread.sleep(100);
    } catch (InterruptedException ie) {
        ie.printStackTrace();
    }
    System.out.println("����a��last����");
    a.last();
}


public void last(){
    System.out.println("����B��last����");
}


}