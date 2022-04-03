package com.ec.survey.tools;
 public class InvalidEmailException extends Exception{

 private  long serialVersionUID;

 private  Object element;

public InvalidEmailException(Object element, String message) {
    super(message);
    this.element = element;
}
public Object getElement(){
    return element;
}


}