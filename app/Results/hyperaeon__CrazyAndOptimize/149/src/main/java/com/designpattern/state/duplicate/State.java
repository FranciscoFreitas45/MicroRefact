package com.designpattern.state.duplicate;
 public class State {

 private  String value;


public String getValue(){
    return value;
}


public void method1(){
    System.out.println("method1 executed");
}


public void method2(){
    System.out.println("method2 executed");
}


public void setValue(String value){
    this.value = value;
}


}