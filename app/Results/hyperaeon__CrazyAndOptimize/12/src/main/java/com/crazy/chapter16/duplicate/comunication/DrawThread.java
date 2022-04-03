package com.crazy.chapter16.duplicate.comunication;
 public class DrawThread extends Thread{

 private  Account account;

 private  double drawAmount;

public DrawThread(String name, Account account, double drawAmount) {
    super(name);
    this.account = account;
    this.drawAmount = drawAmount;
}
public void run(){
    for (int i = 0; i < 100; i++) {
        account.draw(drawAmount);
    }
}


}