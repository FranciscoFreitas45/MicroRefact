package com.crazy.chapter16.duplicate.synchronize;
 import java.util.concurrent.locks.ReentrantLock;
public class AccountLock {

 private  ReentrantLock lock;

 private  String accountNo;

 private  double balance;

public AccountLock() {
}public AccountLock(String accountNo, double balance) {
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
    if (obj != null && obj.getClass() == Account.class) {
        Account account = (Account) obj;
        return account.getAccountNo().equals(accountNo);
    }
    return false;
}


public void draw(double drawAmount){
    lock.lock();
    try {
        if (balance >= drawAmount) {
            System.out.println(Thread.currentThread().getName() + "ȡǮ�ɹ���ȡ����Ʊ��" + drawAmount);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance -= drawAmount;
            System.out.println("\t��" + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + "ȡǮʧ�ܣ�����");
        }
    } finally {
        lock.unlock();
    }
}


public String getAccountNo(){
    return accountNo;
}


}