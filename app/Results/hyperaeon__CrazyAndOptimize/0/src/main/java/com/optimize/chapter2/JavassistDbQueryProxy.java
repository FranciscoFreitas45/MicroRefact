package com.optimize.chapter2;
 import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;
public class JavassistDbQueryProxy {


public IDBQuery creatJavassistDynProxy(){
    ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.setInterfaces(new Class[] { IDBQuery.class });
    Class proxyClass = proxyFactory.createClass();
    IDBQuery javassistProxy = (IDBQuery) proxyClass.newInstance();
    ((ProxyObject) javassistProxy).setHandler(new JavassistDynDbQueryHandler());
    return javassistProxy;
}


}