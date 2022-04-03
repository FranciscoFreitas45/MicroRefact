package com.netease.dto;
 import java.util.Date;
public class CheckRecordDetailDTO {

 private  Date checkDate;

 private  String checkOperator;

 private  String checkReason;


public String getCheckOperator(){
    return checkOperator;
}


public String getCheckReason(){
    return checkReason;
}


public void setCheckReason(String checkReason){
    this.checkReason = checkReason;
}


public Date getCheckDate(){
    return checkDate;
}


public void setCheckOperator(String checkOperator){
    this.checkOperator = checkOperator;
}


public void setCheckDate(Date checkDate){
    this.checkDate = checkDate;
}


}