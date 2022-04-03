package com.optimize.chapter2.trip.proxy;
 import java.lang.reflect.Method;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
public class CglibDbQueryInterceptor implements MethodInterceptor{

 private  IDBQuery real;


@Override
public Object intercept(Object arg0,Method arg1,Object[] arg2,MethodProxy arg3){
    if (real == null) {
        real = new DBQuery();
    }
    return real.request();
}


}