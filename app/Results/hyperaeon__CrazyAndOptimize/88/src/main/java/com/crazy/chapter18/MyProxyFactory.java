package com.crazy.chapter18;
 import java.lang.reflect.Proxy;
public class MyProxyFactory {


public Object getProxy(Object target){
    MyInvocationHandler handler = new MyInvocationHandler();
    handler.setTarget(target);
    return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
}


}