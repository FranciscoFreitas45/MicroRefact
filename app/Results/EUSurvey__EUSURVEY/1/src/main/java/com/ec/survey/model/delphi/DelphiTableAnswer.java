package com.ec.survey.model.delphi;
 public class DelphiTableAnswer {

 private  String question;

 private  String value;

public DelphiTableAnswer(String question, String value) {
    this.question = question;
    this.value = value;
}
public String getQuestion(){
    return question;
}


public String getValue(){
    return value;
}


}