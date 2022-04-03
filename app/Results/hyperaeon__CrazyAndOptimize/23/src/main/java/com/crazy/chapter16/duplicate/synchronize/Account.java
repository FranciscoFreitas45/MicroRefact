package com.crazy.chapter16.duplicate.synchronize;
 public class Account {

 private  String accountNo;

 private  double balance;

public Account() {
}public Account(String accountNo, double balance) {
    this.accountNo = accountNo;
    this.balance = balance;
}
public double getBalance(){
    return balance;
}


@Override
public int hashCode(){
    return accountNo.hashCode();
}


public void setAccountNo(String accountNo){
    this.accountNo = accountNo;
}


@Override
public boolean equals(Object obj){
    if (this == obj)
        return true;
    if (obj != null && obj.getClass() == Account.class) {
        Account target = (Account) obj;
        return target.getAccountNo().equals(accountNo);
    }
    return false;
}


public void setBalance(double balance){
    this.balance = balance;
}


public String getAccountNo(){
    return accountNo;
}


}