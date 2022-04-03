package com.crazy.chapter16;
 public class TraditionalAccount {

 private  String accountNo;

 private  double balance;

 private  boolean flag;

public TraditionalAccount(String accountNo, double balance) {
    this.accountNo = accountNo;
    this.balance = balance;
}
public double getBalance(){
    return balance;
}


public void setAccountNo(String accountNo){
    this.accountNo = accountNo;
}


@Override
public int hashCode(){
    return accountNo.hashCode();
}


@Override
public boolean equals(Object obj){
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    TraditionalAccount other = (TraditionalAccount) obj;
    if (accountNo == null) {
        if (other.accountNo != null)
            return false;
    } else if (!accountNo.equals(other.accountNo))
        return false;
    return true;
}


public void deposit(double depositAmount){
    try {
        if (flag) {
            wait();
        } else {
            System.out.println(Thread.currentThread().getName() + "��Ǯ����Ǯ�����" + depositAmount);
            balance += depositAmount;
            System.out.println("�����" + balance);
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
            System.out.println(Thread.currentThread().getName() + "ȡǮ��ȡǮ�����" + drawAmount);
            balance -= drawAmount;
            System.out.println("�����" + balance);
            flag = false;
            notifyAll();
        }
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}


public String getAccountNo(){
    return accountNo;
}


}