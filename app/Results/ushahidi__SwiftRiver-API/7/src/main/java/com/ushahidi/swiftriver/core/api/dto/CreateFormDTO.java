package com.ushahidi.swiftriver.core.api.dto;
 import java.util.List;
public class CreateFormDTO {

 private  String name;

 private  List<CreateFormFieldDTO> fields;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public List<CreateFormFieldDTO> getFields(){
    return fields;
}


public void setFields(List<CreateFormFieldDTO> fields){
    this.fields = fields;
}


}