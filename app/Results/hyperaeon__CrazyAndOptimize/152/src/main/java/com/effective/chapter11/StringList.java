package com.effective.chapter11;
 import java.io.Serializable;
public class StringList implements Serializable{

 private  long serialVersionUID;

 private  int size;

 private  Entry head;

 private  long serialVersionUID;

 private String data;

 private Entry next;

 private Entry previous;


public Entry getPrevious(){
    return previous;
}


public int getSize(){
    return size;
}


public void setSize(int size){
    this.size = size;
}


public void setData(String data){
    this.data = data;
}


public Entry getHead(){
    return head;
}


public void setNext(Entry next){
    this.next = next;
}


public void setHead(Entry head){
    this.head = head;
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