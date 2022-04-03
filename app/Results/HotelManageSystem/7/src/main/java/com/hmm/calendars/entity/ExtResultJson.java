package com.hmm.calendars.entity;
 import java.util.List;
public class ExtResultJson {

 private  List<T> lists;

public ExtResultJson(List<T> lists) {
    this.lists = lists;
}
public void setLists(List<T> lists){
    this.lists = lists;
}


public List<T> getLists(){
    return lists;
}


}