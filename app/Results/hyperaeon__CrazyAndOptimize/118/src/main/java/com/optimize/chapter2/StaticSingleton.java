package com.optimize.chapter2;
 public class StaticSingleton {

 private  StaticSingleton instance;

private StaticSingleton() {
    System.out.println("StaticSingleton has been created");
}
public StaticSingleton getInstance(){
    return SingletonHolder.instance;
}


}