package com.optimize.chapter2.duplicate;
 import java.lang.reflect.Method;
import javassist.util.proxy.MethodHandler;
public class JavassistDynDbQueryHandler implements MethodHandler{

 private IDBQuery real;


@Override
public Object invoke(Object arg0,Method arg1,Method arg2,Object[] arg3){
    if (real == null) {
        real = new DBQuery();
    }
    return real.request();
}


}