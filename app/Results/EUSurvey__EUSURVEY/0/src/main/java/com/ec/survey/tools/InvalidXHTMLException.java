package com.ec.survey.tools;
 public class InvalidXHTMLException extends Exception{

 private  long serialVersionUID;

 private  Object element;

 public  String REQUIRED;

 public  String TOOSHORT;

 public  String TOOLONG;

 public  String TOOSMALL;

 public  String TOOBIG;

 public  String NOTENOUGH;

 public  String TOOMANY;

 public  String NOTUNIQUE;

 public  String NOTANUMBER;

public InvalidXHTMLException(Object element, String message) {
    super(message);
    this.element = element;
}
public Object getElement(){
    return element;
}


public void setElement(Object element){
    this.element = element;
}


}