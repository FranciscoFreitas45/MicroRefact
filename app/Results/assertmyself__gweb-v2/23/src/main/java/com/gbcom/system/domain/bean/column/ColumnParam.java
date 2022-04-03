package com.gbcom.system.domain.bean.column;
 import javax.xml.bind.annotation;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "columnParam")
public class ColumnParam {

@XmlAttribute(required = true)
 protected  String column;

@XmlAttribute
 protected  String formId;

@XmlAttribute
 protected  String formName;

@XmlAttribute
 protected  String key;

@XmlAttribute
 protected  Boolean isValid;

@XmlAttribute
 protected  Boolean isGridShow;

@XmlAttribute
 protected  String gridName;

@XmlAttribute
 protected  String gridWidth;

@XmlAttribute
 protected  Boolean isExpired;


public String getGridWidth(){
    return gridWidth;
}


public String getKey(){
    return key;
}


public String getFormId(){
    return formId;
}


public void setIsValid(Boolean value){
    this.isValid = value;
}


public void setGridWidth(String value){
    this.gridWidth = value;
}


public String getGridName(){
    return gridName;
}


public void setFormId(String value){
    this.formId = value;
}


public Boolean getIsGridShow(){
    return isGridShow;
}


public Boolean getIsExpired(){
    return isExpired;
}


public void setIsExpired(Boolean expired){
    this.isExpired = expired;
}


public void setColumn(String value){
    this.column = value;
}


public void setGridName(String value){
    this.gridName = value;
}


public String getFormName(){
    return formName;
}


public String getColumn(){
    return column;
}


public void setFormName(String formName){
    this.formName = formName;
}


public Boolean getIsValid(){
    return isValid;
}


public void setIsGridShow(Boolean value){
    this.isGridShow = value;
}


public void setKey(String value){
    this.key = value;
}


}