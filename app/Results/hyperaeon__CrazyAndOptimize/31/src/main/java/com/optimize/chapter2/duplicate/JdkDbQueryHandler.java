package com.optimize.chapter2.duplicate;
 import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
public class JdkDbQueryHandler implements InvocationHandler{

 private  IDBQuery real;


@Override
public Object invoke(Object proxy,Method method,Object[] args){
    if (real == null) {
        real = new DBQuery();
    }
    return real.request();
}


}