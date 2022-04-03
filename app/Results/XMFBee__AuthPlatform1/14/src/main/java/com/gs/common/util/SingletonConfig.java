package com.gs.common.util;
 public class SingletonConfig extends Config{

 private  SingletonConfig config;

private SingletonConfig() {
}
public SingletonConfig getInstance(){
    synchronized (SingletonConfig.class) {
        if (config == null) {
            System.out.println("First time to get a SingletonConfig instance, and only once!");
            config = new SingletonConfig();
        }
    }
    return config;
}


}