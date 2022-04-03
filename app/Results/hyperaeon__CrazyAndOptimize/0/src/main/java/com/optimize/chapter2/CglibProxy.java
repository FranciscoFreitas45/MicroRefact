package com.optimize.chapter2;
 import net.sf.cglib.proxy.Enhancer;
public class CglibProxy {


public IDBQuery createCglibProxy(){
    Enhancer enhancer = new Enhancer();
    enhancer.setCallback(new CglibDbQueryInterceptor());
    enhancer.setInterfaces(new Class[] { IDBQuery.class });
    IDBQuery cglibProxy = (IDBQuery) enhancer.create();
    return cglibProxy;
}


}