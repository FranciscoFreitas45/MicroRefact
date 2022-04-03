package org.jeecgframework.web.cgform.entity.config;
 import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
@ExcelTarget("cgFormFieldVO")
public class CgFormFieldVO {

 private  long serialVersionUID;

 private  java.lang.String id;

@Excel(name = "字段名称", orderNum = "1")
 private  java.lang.String fieldName;

@Excel(name = "字段备注", orderNum = "2")
 private  java.lang.String content;

@Excel(name = "字段类型", orderNum = "3")
 private  java.lang.String type;

@Excel(name = "字段长度", orderNum = "4")
 private  java.lang.String length;

@Excel(name = "小数点长度", orderNum = "5")
 private  java.lang.String pointLength;

@Excel(name = "默认值", orderNum = "6")
 private  java.lang.String fieldDefault;

@Excel(name = "允许空值", orderNum = "7")
 private  java.lang.String isNull;


public void setIsNull(java.lang.String isNull){
    this.isNull = isNull;
}


public void setContent(java.lang.String content){
    this.content = content;
}


public void setFieldDefault(java.lang.String fieldDefault){
    this.fieldDefault = fieldDefault;
}


public void setFieldName(java.lang.String fieldName){
    this.fieldName = fieldName;
}


public java.lang.String getContent(){
    return content;
}


public java.lang.String getId(){
    return id;
}


public void setType(java.lang.String type){
    this.type = type;
}


public void setPointLength(java.lang.String pointLength){
    this.pointLength = pointLength;
}


public void setLength(java.lang.String length){
    this.length = length;
}


public java.lang.String getPointLength(){
    return pointLength;
}


public java.lang.String getType(){
    return type;
}


public void setId(java.lang.String id){
    this.id = id;
}


public java.lang.String getLength(){
    return length;
}


public java.lang.String getIsNull(){
    return isNull;
}


@Override
public String toString(){
    return "CgFormFieldVO [id=" + id + ", fieldName=" + fieldName + ", content=" + content + ", type=" + type + ", length=" + length + ", pointLength=" + pointLength + ", fieldDefault=" + fieldDefault + ", isNull=" + isNull + "]";
}


public java.lang.String getFieldName(){
    return fieldName;
}


public java.lang.String getFieldDefault(){
    return fieldDefault;
}


}