package com.softserve.edu.Resources.dto;
 import java.util.Map;
public class SearchDTO {

 private  String entityType;

 private  Map<String,String> fieldsAndValues;

public SearchDTO(String entityType, Map<String, String> fieldsAndValues) {
    this.entityType = entityType;
    this.fieldsAndValues = fieldsAndValues;
}public SearchDTO() {
}
public void setFieldsAndValues(Map<String,String> fieldsAndValues){
    this.fieldsAndValues = fieldsAndValues;
}


public String getEntityType(){
    return entityType;
}


@Override
public String toString(){
    return "SearchDTO{" + "entityType='" + entityType + '\'' + ", fieldsAndValues=" + fieldsAndValues + '}';
}


public Map<String,String> getFieldsAndValues(){
    return fieldsAndValues;
}


public void setEntityType(String entityType){
    this.entityType = entityType;
}


}