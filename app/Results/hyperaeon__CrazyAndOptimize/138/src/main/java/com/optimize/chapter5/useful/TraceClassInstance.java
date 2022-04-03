package com.optimize.chapter5.useful;
 import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import net.sf.cglib.proxy.MethodInterceptor;
import com.optimize.chapter5.JavaBeanObject;
import com.optimize.chapter5.MyClassLoader;
public class TraceClassInstance {

 static  MyClassLoader cl;

 static  MethodInterceptor mi;


public JavaBeanObject createInstance2(int i){
    CtClass c = ClassPool.getDefault().makeClass("Geym" + i);
    c.setSuperclass(ClassPool.getDefault().get("com.optimize.chapter5.JavaBeanObject"));
    Class clz = c.toClass(cl, null);
    return (JavaBeanObject) clz.newInstance();
}


public void main(String[] args){
    for (int i = 0; i < Integer.MAX_VALUE; i++) {
        JavaBeanObject v = createInstance2(i);
        cl = new MyClassLoader();
    }
}


}