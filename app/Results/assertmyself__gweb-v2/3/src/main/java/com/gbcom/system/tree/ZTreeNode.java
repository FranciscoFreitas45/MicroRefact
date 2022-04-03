package com.gbcom.system.tree;
 import java.util.ArrayList;
import java.util.List;
public class ZTreeNode extends Node{

 private  String id;

 private  String name;

 private  String text;

 private  Boolean check;

 private  Boolean open;

 private  Boolean isParent;

 private  Boolean isLeaf;

 private  String uid;

 private  String type;

 private  String icon;

 private  Boolean extendLeaf;

 private  List<ZTreeNode> children;


public void setName(String name){
    this.name = name;
}


public void setOpen(Boolean open){
    this.open = open;
}


public Boolean getIsLeaf(){
    return isLeaf;
}


public String getName(){
    return name;
}


public Boolean getOpen(){
    return open;
}


public String getText(){
    return text;
}


public Boolean getExtendLeaf(){
    return extendLeaf;
}


public void setCheck(Boolean check){
    this.check = check;
}


public String getId(){
    return id;
}


public void setUid(String uid){
    this.uid = uid;
}


public void setType(String type){
    this.type = type;
}


public String getIcon(){
    return icon;
}


public void setIcon(String icon){
    this.icon = icon;
}


public void setExtendLeaf(Boolean extendLeaf){
    this.extendLeaf = extendLeaf;
}


public List<ZTreeNode> getChildren(){
    return children;
}


public String getUid(){
    return uid;
}


public Boolean getCheck(){
    return check;
}


public String getType(){
    return type;
}


public Boolean getIsParent(){
    return isParent;
}


public void setId(String id){
    this.id = id;
}


public void setIsLeaf(Boolean isLeaf){
    this.isLeaf = isLeaf;
}


public void setIsParent(Boolean parent){
    isParent = parent;
}


public void setChildren(List<ZTreeNode> children){
    this.children = children;
}


public void setText(String text){
    this.text = text;
}


}