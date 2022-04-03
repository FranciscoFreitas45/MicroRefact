package com.ushahidi.swiftriver.core.api.dto.FormValueDTO;
 import java.util.List;
public class FormFieldValue {

 private  String id;

 private  Object value;


public Object getValue(){
    return value;
}


public void setValue(Object value){
    this.value = value;
}


public void setId(String id){
    this.id = id;
}


public String getId(){
    return id;
}


@Override
public String toString(){
    return "FormFieldValue [id=" + id + ", value=" + value + "]";
}


}