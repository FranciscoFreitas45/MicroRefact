package com.optimize.chapter2.duplicate;
 public class StaticSingleton {

 private  StaticSingleton instance;

private StaticSingleton() {
    System.out.println("StaticSingleton is created");
}
public StaticSingleton getInstance(){
    return SingletonHolder.instance;
}


}