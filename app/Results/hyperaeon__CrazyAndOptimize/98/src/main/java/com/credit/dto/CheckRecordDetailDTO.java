package com.credit.dto;
 public class CheckRecordDetailDTO {

 private  String checkDate;

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


public String getCheckDate(){
    return checkDate;
}


public void setCheckOperator(String checkOperator){
    this.checkOperator = checkOperator;
}


public void setCheckDate(String checkDate){
    this.checkDate = checkDate;
}


}