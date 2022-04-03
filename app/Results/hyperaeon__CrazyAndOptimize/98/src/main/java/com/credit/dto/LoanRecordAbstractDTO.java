package com.credit.dto;
 import com.credit.enums.AccountType;
public class LoanRecordAbstractDTO {

 private  int accountAmount;

 private  int unpayedAccount;

 private  int overdueAccount;

 private  int overdueNintyAccount;

 private  int warrantAmount;

 private  AccountType accountType;


public void setUnpayedAccount(int unpayedAccount){
    this.unpayedAccount = unpayedAccount;
}


public void setAccountAmount(int accountAmount){
    this.accountAmount = accountAmount;
}


public int getAccountAmount(){
    return accountAmount;
}


public AccountType getAccountType(){
    return accountType;
}


public void setWarrantAmount(int warrantAmount){
    this.warrantAmount = warrantAmount;
}


public void setAccountType(AccountType accountType){
    this.accountType = accountType;
}


public void setOverdueAccount(int overdueAccount){
    this.overdueAccount = overdueAccount;
}


public int getOverdueNintyAccount(){
    return overdueNintyAccount;
}


public void setOverdueNintyAccount(int overdueNintyAccount){
    this.overdueNintyAccount = overdueNintyAccount;
}


public int getWarrantAmount(){
    return warrantAmount;
}


public int getUnpayedAccount(){
    return unpayedAccount;
}


public int getOverdueAccount(){
    return overdueAccount;
}


}