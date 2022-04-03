package com.crazy.chapter16.duplicate;
 import com.crazy.chapter16.TraditionalAccount;
public class DrawThread extends Thread{

 private  TraditionalAccount account;

 private  double drawAmount;

public DrawThread(String name, TraditionalAccount account, double drawAmount) {
    super(name);
    this.account = account;
    this.drawAmount = drawAmount;
}
public void run(){
    for (int i = 0; i < 100; i++) {
        account.draw(drawAmount);
        ;
    }
}


}