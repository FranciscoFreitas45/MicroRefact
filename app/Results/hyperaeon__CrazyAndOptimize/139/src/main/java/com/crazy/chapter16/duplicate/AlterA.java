package com.crazy.chapter16.duplicate;
 public class AlterA extends Thread{

 private  String str;

public AlterA(String str, String name) {
    super(name);
    this.str = str;
}
public void run(){
    for (int i = 0; i < 20; i++) {
        synchronized (str) {
            str.notify();
            System.out.println(getName() + "  " + i);
            try {
                str.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        synchronized (str) {
            str.notifyAll();
        }
    }
}


}