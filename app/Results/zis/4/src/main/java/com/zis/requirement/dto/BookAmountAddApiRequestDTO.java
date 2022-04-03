package com.zis.requirement.dto;
 import java.util.List;
public class BookAmountAddApiRequestDTO {

 private  Integer departId;

 private  Integer amount;

 private  String operator;

 private  List<Integer> bookIdList;

 private  int grade;

 private  int term;


public Integer getDepartId(){
    return departId;
}


public List<Integer> getBookIdList(){
    return bookIdList;
}


public void setBookIdList(List<Integer> bookIdList){
    this.bookIdList = bookIdList;
}


public int getTerm(){
    return term;
}


public void setDepartId(Integer departId){
    this.departId = departId;
}


public void setGrade(int grade){
    this.grade = grade;
}


public void setOperator(String operator){
    this.operator = operator;
}


public void setAmount(Integer amount){
    this.amount = amount;
}


public String getOperator(){
    return operator;
}


public void setTerm(int term){
    this.term = term;
}


public Integer getAmount(){
    return amount;
}


public int getGrade(){
    return grade;
}


}