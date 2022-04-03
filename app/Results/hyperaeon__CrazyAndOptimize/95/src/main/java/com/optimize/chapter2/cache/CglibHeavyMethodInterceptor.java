package com.optimize.chapter2.cache;
 import java.io.Serializable;
import java.lang.reflect.Method;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
public class CglibHeavyMethodInterceptor implements MethodInterceptor{

 private HeavyMethodDemo real;


@Override
public Object intercept(Object arg0,Method arg1,Object[] arg2,MethodProxy arg3){
    String v = (String) EHCacheUtil.get("cache1", (Serializable) arg2[0]);
    if (v == null) {
        v = real.heavyMethod((Integer) arg2[0]);
        EHCacheUtil.put("cache1", (Integer) arg2[0], v);
    }
    return v;
}


}