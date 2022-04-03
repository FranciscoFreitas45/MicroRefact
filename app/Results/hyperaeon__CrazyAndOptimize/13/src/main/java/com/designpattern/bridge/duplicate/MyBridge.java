package com.designpattern.bridge.duplicate;
 public class MyBridge extends Bridge{


public void method(){
    getSource().method();
}


}