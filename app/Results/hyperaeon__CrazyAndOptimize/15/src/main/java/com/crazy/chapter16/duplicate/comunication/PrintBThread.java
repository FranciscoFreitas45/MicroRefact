package com.crazy.chapter16.duplicate.comunication;
 public class PrintBThread extends Thread{

 private  Swap swap;

public PrintBThread(String name, Swap swap) {
    super(name);
    this.swap = swap;
}
public void run(){
    for (int i = 0; i < 200; i++) {
        swap.printB(i);
    }
}


}