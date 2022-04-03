package com.effective.chapter11.StringList;
 import java.io.Serializable;
public class Entry implements Serializable{

 private  long serialVersionUID;

 private String data;

 private Entry next;

 private Entry previous;


public Entry getPrevious(){
    return previous;
}


public void setData(String data){
    this.data = data;
}


public void setNext(Entry next){
    this.next = next;
}


public Entry getNext(){
    return next;
}


public String getData(){
    return data;
}


public void setPrevious(Entry previous){
    this.previous = previous;
}


}