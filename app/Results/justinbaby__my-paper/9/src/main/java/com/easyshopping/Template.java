package com.easyshopping;
 import java.io.Serializable;
public class Template implements Serializable{

 private  long serialVersionUID;

 private  String id;

 private  Type type;

 private  String name;

 private  String templatePath;

 private  String staticPath;

 private  String description;


public void setName(String name){
    this.name = name;
}


public String getTemplatePath(){
    return templatePath;
}


public String getStaticPath(){
    return staticPath;
}


public String getName(){
    return name;
}


public Type getType(){
    return type;
}


public void setId(String id){
    this.id = id;
}


public String getId(){
    return id;
}


public void setStaticPath(String staticPath){
    this.staticPath = staticPath;
}


public void setDescription(String description){
    this.description = description;
}


public void setType(Type type){
    this.type = type;
}


public void setTemplatePath(String templatePath){
    this.templatePath = templatePath;
}


public String getDescription(){
    return description;
}


}