package org.jeecgframework.tag.vo.easyui;
 import java.util.Map;
public class TreeGridModel {

 private  String idField;

 private  String textField;

 private  String childList;

 private  String parentId;

 private  String parentText;

 private  String code;

 private  String src;

 private  String roleid;

 private  String icon;

 private  String order;

 private  String functionType;

 private  String iconStyle;

 private  Map<String,Object> fieldMap;


public void setIdField(String idField){
    this.idField = idField;
}


public String getIdField(){
    return idField;
}


public Map<String,Object> getFieldMap(){
    return fieldMap;
}


public String getParentText(){
    return parentText;
}


public void setTextField(String textField){
    this.textField = textField;
}


public void setChildList(String childList){
    this.childList = childList;
}


public void setIconStyle(String iconStyle){
    this.iconStyle = iconStyle;
}


public void setFunctionType(String functionType){
    this.functionType = functionType;
}


public String getChildList(){
    return childList;
}


public String getCode(){
    return code;
}


public void setSrc(String src){
    this.src = src;
}


public String getSrc(){
    return src;
}


public String getIconStyle(){
    return iconStyle;
}


public void setCode(String code){
    this.code = code;
}


public String getTextField(){
    return textField;
}


public String getRoleid(){
    return roleid;
}


public String getIcon(){
    return icon;
}


public void setIcon(String icon){
    this.icon = icon;
}


public void setRoleid(String roleid){
    this.roleid = roleid;
}


public void setOrder(String order){
    this.order = order;
}


public String getFunctionType(){
    return functionType;
}


public void setParentText(String parentText){
    this.parentText = parentText;
}


public void setFieldMap(Map<String,Object> fieldMap){
    this.fieldMap = fieldMap;
}


public String getOrder(){
    return order;
}


public void setParentId(String parentId){
    this.parentId = parentId;
}


public String getParentId(){
    return parentId;
}


}