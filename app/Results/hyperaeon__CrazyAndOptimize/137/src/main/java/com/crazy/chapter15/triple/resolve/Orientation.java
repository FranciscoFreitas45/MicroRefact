package com.crazy.chapter15.triple.resolve;
 import java.io.ObjectStreamException;
import java.io.Serializable;
public class Orientation implements Serializable{

 private  long serialVersionUID;

 public  Orientation HORIZONTAL;

 public  Orientation VERTICAL;

 private  int value;

private Orientation(int value) {
    this.value = value;
}
public Object readResolve(){
    if (value == 1) {
        return HORIZONTAL;
    } else if (value == 2) {
        return VERTICAL;
    }
    return null;
}


}