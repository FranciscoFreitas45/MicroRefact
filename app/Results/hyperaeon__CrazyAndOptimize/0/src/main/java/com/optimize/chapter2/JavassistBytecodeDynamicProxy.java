package com.optimize.chapter2;
 import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtNewConstructor;
import javassist.CtNewMethod;
public class JavassistBytecodeDynamicProxy {


public IDBQuery createJavassistBytecodeDynamicProxy(){
    ClassPool mPool = new ClassPool(true);
    CtClass mCtc = mPool.makeClass(IDBQuery.class.getName() + "JavassistBytecodeProxy");
    mCtc.addInterface(mPool.get(IDBQuery.class.getName()));
    mCtc.addConstructor(CtNewConstructor.defaultConstructor(mCtc));
    mCtc.addField(CtField.make("public " + IDBQuery.class.getName() + " real", mCtc));
    String dbqueryname = DBQuery.class.getName();
    mCtc.addMethod(CtNewMethod.make("public String request(, declaring) { if (real == null ) { real = new " + dbqueryname + "(); return real.request(); }", mCtc));
    Class pc = mCtc.toClass();
    IDBQuery bytecodeProxy = (IDBQuery) pc.newInstance();
    return bytecodeProxy;
}


}