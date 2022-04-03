package com.circle.pojo.msg;
 public class MessageTypeBean {

 public  String id;

 private  String typeName;

 private  String classpath;

 private  String button1;

 private  String button2;


public String getClasspath(){
    return classpath;
}


public void setClasspath(String classpath){
    this.classpath = classpath;
}


public String getButton2(){
    return button2;
}


public String getButton1(){
    return button1;
}


public void setTypeName(String typeName){
    this.typeName = typeName;
}


public void setId(String id){
    this.id = id;
}


public String getId(){
    return id;
}


public String getTypeName(){
    return typeName;
}


public void setButton1(String button1){
    this.button1 = button1;
}


public void setButton2(String button2){
    this.button2 = button2;
}


}