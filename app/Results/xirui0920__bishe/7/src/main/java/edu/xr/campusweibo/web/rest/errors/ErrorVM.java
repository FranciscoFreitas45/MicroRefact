package edu.xr.campusweibo.web.rest.errors;
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class ErrorVM implements Serializable{

 private  long serialVersionUID;

 private  String message;

 private  String description;

 private  List<FieldErrorVM> fieldErrors;

public ErrorVM(String message) {
    this(message, null);
}public ErrorVM(String message, String description) {
    this.message = message;
    this.description = description;
}public ErrorVM(String message, String description, List<FieldErrorVM> fieldErrors) {
    this.message = message;
    this.description = description;
    this.fieldErrors = fieldErrors;
}
public void add(String objectName,String field,String message){
    if (fieldErrors == null) {
        fieldErrors = new ArrayList<>();
    }
    fieldErrors.add(new FieldErrorVM(objectName, field, message));
}


public List<FieldErrorVM> getFieldErrors(){
    return fieldErrors;
}


public String getMessage(){
    return message;
}


public String getDescription(){
    return description;
}


}