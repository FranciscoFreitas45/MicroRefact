package com.kingen.vo;
 import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
public class TreeNode {

 private  String id;

 private  String parentId;

 private  String text;

 private  boolean leaf;

 private  String iconCls;

 private  List<TreeNode> children;

 private  TreeNode parent;


public String getIconCls(){
    return iconCls;
}


@JsonIgnore
public TreeNode getParent(){
    return parent;
}


public void setIconCls(String iconCls){
    this.iconCls = iconCls;
}


public String getText(){
    return text;
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


public List<TreeNode> getChildren(){
    return children;
}


public void setId(String id){
    this.id = id;
}


public void setParentId(String parentId){
    this.parentId = parentId;
}


public void setParent(TreeNode parent){
    this.parent = parent;
}


public void addChild(TreeNode node){
    if (children == null) {
        children = Lists.newArrayList();
    }
    children.add(node);
}


public void setChildren(List<TreeNode> children){
    this.children = children;
}


@JsonIgnore
public String getParentId(){
    return parentId;
}


public void setText(String text){
    this.text = text;
}


}