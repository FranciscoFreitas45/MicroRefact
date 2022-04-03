package com.optimize.chapter2.cache;
 import net.sf.cglib.proxy.Enhancer;
public class DemoWithCache {


public HeavyMethodDemo neweavyMethod(){
    return new HeavyMethodDemo();
}


public HeavyMethodDemo newCacheHeavyMethod(){
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(HeavyMethodDemo.class);
    enhancer.setCallback(new CglibHeavyMethodInterceptor());
    HeavyMethodDemo cglibProxy = (HeavyMethodDemo) enhancer.create();
    return cglibProxy;
}


}