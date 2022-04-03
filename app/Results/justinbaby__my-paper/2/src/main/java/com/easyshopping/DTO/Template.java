package com.easyshopping.DTO;
 import java.io.Serializable;
public class Template implements Serializable{

 private  long serialVersionUID;

 private  String id;

 private  Type type;

 private  String name;

 private  String templatePath;

 private  String staticPath;

 private  String description;


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


public String getId(){
    return id;
}


public String getDescription(){
    return description;
}


}