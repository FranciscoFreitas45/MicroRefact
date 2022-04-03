package com.kingen.bean;
 import java.util.List;
public class MenuData {

 private  List<MenuData> children;

 private  boolean expanded;

 private  String FMenuId;

 private  String id;

 private  boolean leaf;

 private  String text;

 private  String title;

 private  String iconCls;

 private  String icon;


public boolean isExpanded(){
    return expanded;
}


public String getIconCls(){
    return iconCls;
}


public void setIconCls(String iconCls){
    this.iconCls = iconCls;
}


public void setExpanded(boolean expanded){
    this.expanded = expanded;
}


public String getText(){
    return text;
}


public void setTitle(String title){
    this.title = title;
}


public String getId(){
    return id;
}


public void setLeaf(boolean leaf){
    this.leaf = leaf;
}


public boolean isLeaf(){
    return leaf;
}


public String getIcon(){
    return icon;
}


public void setIcon(String icon){
    this.icon = icon;
}


public List<MenuData> getChildren(){
    return children;
}


public String getTitle(){
    return title;
}


public void setId(String id){
    this.id = id;
}


public String getFMenuId(){
    return FMenuId;
}


public void setFMenuId(String fMenuId){
    FMenuId = fMenuId;
}


public void setChildren(List<MenuData> children){
    this.children = children;
}


public void setText(String text){
    this.text = text;
}


}