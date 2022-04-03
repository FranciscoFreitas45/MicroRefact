package com.yalcin.dto.request;
 import javax.persistence.Column;
import javax.validation.constraints.Size;
public class PurseForm {

@Size(min = 1, max = 50)
@Column(name = "balance")
 private  String balance;


public String getBalance(){
    return balance;
}


public void setBalance(String balance){
    this.balance = balance;
}


}