package com.gbcom.DTO;
 import javax.xml.bind.annotation;
public class Constraint {

 protected  String type;

 protected  Integer length;

 protected  Integer precesion;

 protected  boolean readonly;

 protected  String expression;

 protected  boolean visible;

 protected  String definition;

 protected  String _default;


public String getDefinition(){
    return definition;
}


public Integer getPrecesion(){
    return precesion;
}


public String getDefault(){
    return _default;
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


}