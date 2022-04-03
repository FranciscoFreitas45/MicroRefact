package com.ushahidi.swiftriver.core.DTO;
 import java.util.List;
public class FormValueDTO {

 private  String id;

 private  List<FormFieldValue> values;

 private  String id;

 private  Object value;


public Object getValue(){
    return value;
}


public List<FormFieldValue> getValues(){
    return values;
}


public void setValues(List<FormFieldValue> values){
    this.values = values;
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