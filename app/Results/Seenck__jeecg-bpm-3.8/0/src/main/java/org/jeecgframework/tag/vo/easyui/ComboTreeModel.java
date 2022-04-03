package org.jeecgframework.tag.vo.easyui;
 public class ComboTreeModel {

 private  String idField;

 private  String textField;

 private  String iconCls;

 private  String childField;

 private  String srcField;

public ComboTreeModel(String idField, String textField, String childField) {
    this.idField = idField;
    this.textField = textField;
    this.childField = childField;
}public ComboTreeModel(String idField, String textField, String childField, String srcField) {
    this.idField = idField;
    this.textField = textField;
    this.childField = childField;
    this.srcField = srcField;
}
public void setIdField(String idField){
    this.idField = idField;
}


public String getIconCls(){
    return iconCls;
}


public String getIdField(){
    return idField;
}


public void setSrcField(String srcField){
    this.srcField = srcField;
}


public void setIconCls(String iconCls){
    this.iconCls = iconCls;
}


public String getChildField(){
    return childField;
}


public void setChildField(String childField){
    this.childField = childField;
}


public String getTextField(){
    return textField;
}


public void setTextField(String textField){
    this.textField = textField;
}


public String getSrcField(){
    return srcField;
}


}