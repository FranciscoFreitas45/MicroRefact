package com.uec.imonitor.common.base;
 import java.util.List;
public class PageResponse {

 private  List<T> content;

 private  int number;

 private  int numberOfElements;

 private  long totalElements;

 private  int totalPage;


public int getNumber(){
    return number;
}


public void setNumberOfElements(int numberOfElements){
    this.numberOfElements = numberOfElements;
}


public long getTotalElements(){
    return totalElements;
}


public void setContent(List<T> content){
    this.content = content;
}


public void setTotalElements(long totalElements){
    this.totalElements = totalElements;
}


public int getNumberOfElements(){
    return numberOfElements;
}


public List<T> getContent(){
    return content;
}


public void setTotalPage(int totalPage){
    this.totalPage = totalPage;
}


public void setNumber(int number){
    this.number = number;
}


public int getTotalPage(){
    return totalPage;
}


}