package com.kingen.bean;
 import java.util.List;
public class RightData {

 private  String menuId;

 private  String menuName;

 private  String pmenuId;

 private  String Note;

 private  Boolean checked;

 private  Boolean leaf;

 private  Boolean expanded;

 private  List<RightData> children;


public void setExpanded(Boolean expanded){
    this.expanded = expanded;
}


public String getMenuId(){
    return menuId;
}


public Boolean getExpanded(){
    return expanded;
}


public String getNote(){
    return Note;
}


public void setLeaf(Boolean leaf){
    this.leaf = leaf;
}


public List<RightData> getChildren(){
    return children;
}


public void setMenuId(String menuId){
    this.menuId = menuId;
}


public void setPmenuId(String pmenuId){
    this.pmenuId = pmenuId;
}


public Boolean getLeaf(){
    return leaf;
}


public void setChecked(Boolean checked){
    this.checked = checked;
}


public void setNote(String note){
    Note = note;
}


public String getMenuName(){
    return menuName;
}


public Boolean getChecked(){
    return checked;
}


public void setChildren(List<RightData> children){
    this.children = children;
}


public void setMenuName(String menuName){
    this.menuName = menuName;
}


public String getPmenuId(){
    return pmenuId;
}


}