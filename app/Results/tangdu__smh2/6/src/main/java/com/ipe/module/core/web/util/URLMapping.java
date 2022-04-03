package com.ipe.module.core.web.util;
 public class URLMapping {

 private  String consumes;

 private  String custom;

 private  String headers;

 private  String methods;

 private  String params;

 private  String produces;

 private  String url;

 private  String methodName;

 private  String className;

 private  String returnType;

 private  String parameters;

 private  String annotationName;


public void setHeaders(String headers){
    this.headers = headers;
}


public void setConsumes(String consumes){
    this.consumes = consumes;
}


public void setMethods(String methods){
    this.methods = methods;
}


public String getHeaders(){
    return headers;
}


public String getMethodName(){
    return methodName;
}


public void setMethodName(String methodName){
    this.methodName = methodName;
}


public String getAnnotationName(){
    return annotationName;
}


public String getCustom(){
    return custom;
}


public void setProduces(String produces){
    this.produces = produces;
}


public void setClassName(String className){
    this.className = className;
}


public String getConsumes(){
    return consumes;
}


public String getProduces(){
    return produces;
}


public void setUrl(String url){
    this.url = url;
}


public void setParameters(String parameters){
    this.parameters = parameters;
}


public void setCustom(String custom){
    this.custom = custom;
}


public String getUrl(){
    return url;
}


public String getReturnType(){
    return returnType;
}


public void setReturnType(String returnType){
    this.returnType = returnType;
}


public void setAnnotationName(String annotationName){
    this.annotationName = annotationName;
}


public String getParameters(){
    return parameters;
}


public String getClassName(){
    return className;
}


public String getMethods(){
    return methods;
}


public String getParams(){
    return params;
}


public void setParams(String params){
    this.params = params;
}


}