package com.softserve.edu.Resources.dto;
 import java.util.ArrayList;
import java.util.List;
public class ValidationErrorDTO {

 private  List<FieldErrorDTO> fieldErrors;

public ValidationErrorDTO() {
}
public void setFieldErrors(List<FieldErrorDTO> fieldErrors){
    this.fieldErrors = fieldErrors;
}


public List<FieldErrorDTO> getFieldErrors(){
    return fieldErrors;
}


public void addFieldError(String path,String message){
    FieldErrorDTO error = new FieldErrorDTO(path, message);
    fieldErrors.add(error);
}


}