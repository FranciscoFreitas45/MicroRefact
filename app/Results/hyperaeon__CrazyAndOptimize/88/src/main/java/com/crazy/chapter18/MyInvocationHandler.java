package com.crazy.chapter18;
 import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
public class MyInvocationHandler implements InvocationHandler{

 private  Object target;


public void setTarget(Object target){
    this.target = target;
}


@Override
public Object invoke(Object proxy,Method method,Object[] args){
    PersonUtils util = new PersonUtils();
    util.method1();
    Object result = method.invoke(target, args);
    util.method2();
    return result;
}


}