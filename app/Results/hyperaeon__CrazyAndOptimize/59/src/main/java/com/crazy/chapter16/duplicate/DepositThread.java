package com.crazy.chapter16.duplicate;
 import com.crazy.chapter16.TraditionalAccount;
public class DepositThread extends Thread{

 private  TraditionalAccount account;

 private  double depositAmount;

public DepositThread(String name, TraditionalAccount account, double depositAmount) {
    super(name);
    this.account = account;
    this.depositAmount = depositAmount;
}
public void run(){
    for (int i = 0; i < 100; i++) {
        account.deposit(depositAmount);
    }
}


}