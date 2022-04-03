package com.effective.chapter11;
 import java.io.Serializable;
public class ElvisStealer implements Serializable{

 private  long serialVersionUID;

 static  Elvis impersonator;

 private  Elvis payload;


public Object readResolve(){
    impersonator = payload;
    return new String[] { "A Fool Such as I" };
}


}