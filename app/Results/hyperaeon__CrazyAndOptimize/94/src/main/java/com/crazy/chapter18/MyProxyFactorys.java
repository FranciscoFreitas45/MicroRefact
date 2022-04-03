package com.crazy.chapter18;
 import java.lang.reflect.Proxy;
public class MyProxyFactorys {


public Object getProxy(Object target){
    MyInvocationHandlers handler = new MyInvocationHandlers();
    handler.setTarget(target);
    return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
}


}