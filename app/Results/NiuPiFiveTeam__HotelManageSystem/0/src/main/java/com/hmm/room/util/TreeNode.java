package com.hmm.room.util;
 import java.util.ArrayList;
import java.util.List;
public class TreeNode {

 private  Long id;

 private  String text;

 private  boolean expanded;

 private  boolean leaf;

 private  List<TreeNode> children;

 private  String iconCls;


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


public Long getId(){
    return id;
}


public void setLeaf(boolean leaf){
    this.leaf = leaf;
}


public boolean isLeaf(){
    return leaf;
}


public List<TreeNode> getChildren(){
    return children;
}


public void setId(Long id){
    this.id = id;
}


@Override
public String toString(){
    return "TreeNode [id=" + id + ", text=" + text + ", expanded=" + expanded + ", leaf=" + leaf + ", children=" + children + ", iconCls=" + iconCls + "]";
}


public void setChildren(List<TreeNode> children){
    this.children = children;
}


public void setText(String text){
    this.text = text;
}


}