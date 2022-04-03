package com.optimize.chapter3.reference;
 import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
public class MyObject {


@Override
public String toString(){
    return "I am MyObject";
}


@Override
public void finalize(){
    super.finalize();
    System.out.println("MyObject's finalize called");
}


public void main(String[] args){
    MyObject obj = new MyObject();
    ReferenceQueue<MyObject> softQueue = new ReferenceQueue<MyObject>();
    SoftReference<MyObject> softRef = new SoftReference<MyObject>(obj, softQueue);
    obj = null;
    System.gc();
    System.out.println("After GC: soft get = " + softRef.get());
    System.out.println("�������ڴ�");
    byte[] b = new byte[4 * 1024 * 928];
    System.out.println("After new byte[] : soft get = " + softRef.get());
}


}