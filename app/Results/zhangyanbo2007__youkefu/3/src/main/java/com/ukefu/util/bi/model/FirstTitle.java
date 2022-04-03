package com.ukefu.util.bi.model;
 public class FirstTitle {

 private  long serialVersionUID;

 private  String name;

 private  int depth;

 private  String rename;

 private  String level;

 private  String description;

public FirstTitle() {
}public FirstTitle(String name, int depth, boolean measure) {
    this.name = name;
    this.depth = depth;
}public FirstTitle(String name) {
    this.name = name;
}
public void setName(String name){
    this.name = name;
}


public String getLevel(){
    return level;
}


public String getName(){
    return name;
}


public int getDepth(){
    return depth;
}


public String getRename(){
    return rename;
}


public void setRename(String rename){
    this.rename = rename;
}


public String toString(){
    return this.name;
}


public void setDescription(String description){
    this.description = description;
}


public void setDepth(int depth){
    this.depth = depth;
}


public String getDescription(){
    return description;
}


public void setLevel(String level){
    this.level = level;
}


}