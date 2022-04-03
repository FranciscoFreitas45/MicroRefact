package com.empl.mgr.dto;
 import java.io.Serializable;
public class PageDto implements Serializable{

 private  long serialVersionUID;

 private  int count;

 private  int totalPage;

 private  int nowPage;

 private  int number;

public PageDto() {
// TODO Auto-generated constructor stub
}
public int getNumber(){
    return number;
}


public void setTotalPage(int totalPage){
    this.totalPage = totalPage;
}


public int getNowPage(){
    return nowPage;
}


@Override
public String toString(){
    return "PageDto [count:" + count + ", totalPage:" + totalPage + ", nowPage:" + nowPage + ", number:" + number + "]";
}


public void setNowPage(int nowPage){
    this.nowPage = nowPage;
}


public int getCount(){
    return count;
}


public void setCount(int count){
    this.count = count;
}


public void setNumber(int number){
    this.number = number;
}


public int getTotalPage(){
    return totalPage;
}


}