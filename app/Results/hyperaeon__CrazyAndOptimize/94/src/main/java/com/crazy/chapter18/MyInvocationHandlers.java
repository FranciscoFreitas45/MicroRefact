package com.crazy.chapter18;
 import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
public class MyInvocationHandlers implements InvocationHandler{

 private  Object target;


public void setTarget(Object target){
    this.target = target;
}


@Override
public Object invoke(Object proxy,Method method,Object[] args){
    DogUtil du = new DogUtil();
    du.method1();
    Object result = method.invoke(target, args);
    du.method2();
    return result;
}


}