package com.designpattern.singleton;
 public class Singleton {

 private  Singleton instance;

private Singleton() {
}
public Object readResolve(){
    return getInstance();
}


public Singleton getInstance(){
    return SingletonFactory.instance;
}


}