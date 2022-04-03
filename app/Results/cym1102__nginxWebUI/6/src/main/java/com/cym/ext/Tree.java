package com.cym.ext;
 import java.util.List;
public class Tree {

 private String name;

 private String value;

 private List<Tree> children;


public void setName(String name){
    this.name = name;
}


public String getValue(){
    return value;
}


public List<Tree> getChildren(){
    return children;
}


public String getName(){
    return name;
}


public void setValue(String value){
    this.value = value;
}


public void setChildren(List<Tree> children){
    this.children = children;
}


}