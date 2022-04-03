package com.ushahidi.swiftriver.core.api.dto;
 import java.util.List;
public class CreateFormFieldDTO {

 private  String title;

 private  String description;

 private  String type;

 private  boolean required;

 private  List<String> options;


public boolean isRequired(){
    return required;
}


public void setRequired(boolean required){
    this.required = required;
}


public String getTitle(){
    return title;
}


public String getType(){
    return type;
}


public void setTitle(String title){
    this.title = title;
}


public void setOptions(List<String> options){
    this.options = options;
}


public void setDescription(String description){
    this.description = description;
}


public List<String> getOptions(){
    return options;
}


public void setType(String type){
    this.type = type;
}


public String getDescription(){
    return description;
}


}