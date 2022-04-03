package com.crazy.chapter16.duplicate.synchronize;
 public class DrawThread3 extends Thread{

 private  AccountSafe account;

 private  double drawAmount;

public DrawThread3(String name, AccountSafe account, double drawAmount) {
    super(name);
    this.account = account;
    this.drawAmount = drawAmount;
}
public void run(){
    account.draw(drawAmount);
}


}