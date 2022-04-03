package com.ec.survey.model;
 public class ImportTranslationResult {

 private  boolean success;

 private  String message;

 private  String language;

 private  String uid;

 private  int surveyId;

 private  boolean exists;

 private  boolean ignored;

 private  String[] pivotLabels;

 private  String[] keys;

 private  String[] labels;

 private  String[] invalidKeys;

public ImportTranslationResult() {
}
public void setPivotLabels(String[] pivotLabels){
    this.pivotLabels = pivotLabels;
}


public void setIgnored(boolean ignored){
    this.ignored = ignored;
}


public String getLanguage(){
    return language;
}


public String getMessage(){
    return message;
}


public String[] getKeys(){
    return keys;
}


public void setMessage(String message){
    this.message = message;
}


public void setExists(boolean exists){
    this.exists = exists;
}


public void setUid(String uid){
    this.uid = uid;
}


public boolean isIgnored(){
    return ignored;
}


public void setInvalidKeys(String[] invalidKeys){
    this.invalidKeys = invalidKeys;
}


public void setSuccess(boolean success){
    this.success = success;
}


public String[] getPivotLabels(){
    return pivotLabels;
}


public String[] getLabels(){
    return labels;
}


public String getUid(){
    return uid;
}


public boolean isExists(){
    return exists;
}


public String[] getInvalidKeys(){
    return invalidKeys;
}


public int getSurveyId(){
    return surveyId;
}


public void setKeys(String[] keys){
    this.keys = keys;
}


public void setLabels(String[] labels){
    this.labels = labels;
}


public void setSurveyId(int surveyId){
    this.surveyId = surveyId;
}


public boolean isSuccess(){
    return success;
}


public void setLanguage(String language){
    this.language = language;
}


}