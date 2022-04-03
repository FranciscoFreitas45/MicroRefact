package com.designpattern.singleton.duplicate;
 public class Singleton2 {

 private  Singleton2 instance;


public Singleton2 getInstance(){
    return SingletonFacotry.instance;
}


}