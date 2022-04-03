package com.cym.ext;
 public class TreeNode {

 private String id;

 private String pid;

 private String name;

 private String isParent;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public String getIsParent(){
    return isParent;
}


public void setId(String id){
    this.id = id;
}


public String getId(){
    return id;
}


public void setPid(String pid){
    this.pid = pid;
}


public void setIsParent(String isParent){
    this.isParent = isParent;
}


public String getPid(){
    return pid;
}


}