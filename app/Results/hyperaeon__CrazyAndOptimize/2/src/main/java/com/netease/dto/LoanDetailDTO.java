package com.netease.dto;
 import java.util.ArrayList;
import java.util.List;
import com.netease.enums.AccountType;
public class LoanDetailDTO {

 private  AccountType accountType;

 private  String detailName;

 private  List<String> detailList;


public void setDetailName(String detailName){
    this.detailName = detailName;
}


public AccountType getAccountType(){
    return accountType;
}


public void setAccountType(AccountType accountType){
    this.accountType = accountType;
}


public String getDetailName(){
    return detailName;
}


public List<String> getDetailList(){
    return detailList;
}


public void setDetailList(List<String> detailList){
    this.detailList = detailList;
}


}