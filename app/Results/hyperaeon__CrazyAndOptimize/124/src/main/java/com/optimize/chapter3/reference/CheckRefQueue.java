package com.optimize.chapter3.reference;
 import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
public class CheckRefQueue {


public void main(String[] args){
    MyObject obj = new MyObject();
    ReferenceQueue<MyObject> softQueue = new ReferenceQueue<MyObject>();
    SoftReference<MyObject> softRef = new SoftReference<>(obj, softQueue);
    new CheckQueue().start();
    obj = null;
    System.gc();
    System.out.println(softRef.get());
    byte[] b = new byte[4 * 1024 * 1024];
    System.out.println(softRef.get());
}


}