package com.ipe.module.ext.pojo;
 public class ExtColumn {

 private  String javaType;

 private  Integer maxLength;

 private  String name;

 private  String fieldLabel;

 private  String xtype;

 private  boolean allowBlank;

 private  Integer sno;


public void setName(String name){
    this.name = name;
}


public boolean isAllowBlank(){
    return allowBlank;
}


public void setAllowBlank(boolean allowBlank){
    this.allowBlank = allowBlank;
}


public String getName(){
    return name;
}


public void setXtype(String xtype){
    this.xtype = xtype;
}


public void setFieldLabel(String fieldLabel){
    this.fieldLabel = fieldLabel;
}


public void setSno(Integer sno){
    this.sno = sno;
}


public String getXtype(){
    return xtype;
}


public Integer getMaxLength(){
    return maxLength;
}


public Integer getSno(){
    return sno;
}


public void setJavaType(String javaType){
    this.javaType = javaType;
}


public String getJavaType(){
    return javaType;
}


public void setMaxLength(Integer maxLength){
    this.maxLength = maxLength;
}


public String getFieldLabel(){
    return fieldLabel;
}


}