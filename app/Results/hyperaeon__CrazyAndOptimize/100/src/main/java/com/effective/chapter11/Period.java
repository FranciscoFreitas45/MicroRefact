package com.effective.chapter11;
 import java.io.Serializable;
import java.util.Date;
public class Period implements Serializable{

 private  long serialVersionUID;

 private  Date start;

 private  Date end;

public Period(Date start, Date end) {
    // this.start = start;
    // this.end = end;
    this.start = new Date(start.getTime());
    this.end = new Date(end.getTime());
    if (start.compareTo(end) > 0) {
        throw new IllegalArgumentException(start + " after " + end);
    }
}
public Date getStart(){
    return new Date(start.getTime());
}


public String toString(){
    return start.toString() + end.toString();
}


public Date getEnd(){
    return new Date(end.getTime());
}


}