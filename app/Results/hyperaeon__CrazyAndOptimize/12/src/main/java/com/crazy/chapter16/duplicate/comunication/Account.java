package com.crazy.chapter16.duplicate.comunication;
 public class Account {

 private  String accountNo;

 private  double balance;

 private  boolean flag;

public Account(String accountNo, double balance) {
    this.accountNo = accountNo;
    this.balance = balance;
}
public double getBalance(){
    return balance;
}


public void deposit(double depositAmount){
    try {
        if (flag) {
            wait();
        } else {
            System.out.println(Thread.currentThread().getName() + " ��Ǯ��" + depositAmount);
            balance += depositAmount;
            System.out.println("�˻����Ϊ��" + balance);
            flag = true;
            notifyAll();
        }
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}


public void draw(double drawAmount){
    try {
        if (!flag) {
            wait();
        } else {
            if (balance >= drawAmount) {
                System.out.println(Thread.currentThread().getName() + " ȡǮ��" + drawAmount);
                balance -= drawAmount;
                System.out.println("�˻����Ϊ��" + balance);
                flag = false;
                notifyAll();
            } else {
                System.out.println(Thread.currentThread().getName() + "ȡǮʱ�����㣡");
                flag = false;
                notifyAll();
            }
        }
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}


}