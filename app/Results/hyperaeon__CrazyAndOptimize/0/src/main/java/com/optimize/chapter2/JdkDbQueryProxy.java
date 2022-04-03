package com.optimize.chapter2;
 import java.lang.reflect.Proxy;
public class JdkDbQueryProxy {


public IDBQuery createJdkProxy(){
    IDBQuery jdkProxy = (IDBQuery) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[] { IDBQuery.class }, new JdkDbQueryHandler());
    return jdkProxy;
}


}