package com.ec.survey.service;
 public class SurveyException extends Exception{

 private  long serialVersionUID;

 private  int surveyID;

public SurveyException(Integer surveyID) {
    this.surveyID = surveyID;
}
public int getSurveyID(){
    return surveyID;
}


}