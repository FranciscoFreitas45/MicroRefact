package com.softserve.edu.Resources.dto;
 import com.softserve.edu.Resources.entity.ValueType;
public class ValueTypeDTO {

 public  String title;

 public  ValueType valueType;

 public  String defaultPattern;

public ValueTypeDTO(ValueType valueType) {
    this.valueType = valueType;
    title = valueType.typeName;
    defaultPattern = valueType.getDefaultPattern();
}
}