package com.crazy.chapter15.duplicate;
 import java.io.Serializable;
public class Human implements Serializable{

 private  long serialVersionUID;

 protected  int arm;


public void setArm(int arm){
    this.arm = arm;
}


public int getArm(){
    return arm;
}


}