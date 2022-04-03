package com.gbcom.system.domain.bean.param;
 import javax.xml.bind.annotation;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Constraint")
public class Constraint {

@XmlAttribute(required = true)
 protected  String type;

@XmlAttribute
 protected  Integer length;

@XmlAttribute
 protected  Integer precesion;

@XmlAttribute(required = true)
 protected  boolean readonly;

@XmlAttribute
 protected  String expression;

@XmlAttribute(required = true)
 protected  boolean visible;

@XmlAttribute
 protected  String definition;

@XmlAttribute(name = "default")
 protected  String _default;


public void setVisible(boolean value){
    this.visible = value;
}


public String getDefinition(){
    return definition;
}


public boolean isReadonly(){
    return readonly;
}


public Integer getPrecesion(){
    return precesion;
}


public String getDefault(){
    return _default;
}


public boolean isVisible(){
    return visible;
}


public void setType(String value){
    this.type = value;
}


public void setReadonly(boolean value){
    this.readonly = value;
}


public void setDefault(String value){
    this._default = value;
}


public void setLength(Integer value){
    this.length = value;
}


public String getExpression(){
    return expression;
}


public String getType(){
    return type;
}


public Integer getLength(){
    return length;
}


public void setPrecesion(Integer value){
    this.precesion = value;
}


public void setExpression(String value){
    this.expression = value;
}


public void setDefinition(String definition){
    this.definition = definition;
}


}