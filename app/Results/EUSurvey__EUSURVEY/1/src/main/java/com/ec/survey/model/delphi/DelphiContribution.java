package com.ec.survey.model.delphi;
 import java.util.Date;
public class DelphiContribution {

 private  int answerSetId;

 private  String answerSetUniqueCode;

 private  String answerUid;

 private  int column;

 private  String explanation;

 private  String questionUid;

 private  int row;

 private  Date update;

 private  String value;


public String getAnswerSetUniqueCode(){
    return answerSetUniqueCode;
}


public String getAnswerUid(){
    return answerUid;
}


public void setAnswerUid(String answerUid){
    this.answerUid = answerUid;
}


public String getQuestionUid(){
    return questionUid;
}


public void setExplanation(String explanation){
    this.explanation = explanation;
}


public void setAnswerSetId(int answerSetId){
    this.answerSetId = answerSetId;
}


public void setUpdate(Date update){
    this.update = update;
}


public Date getUpdate(){
    return update;
}


public int getRow(){
    return row;
}


public String getExplanation(){
    return explanation;
}


public String getValue(){
    return value;
}


public void setAnswerSetUniqueCode(String answerSetUniqueCode){
    this.answerSetUniqueCode = answerSetUniqueCode;
}


public void setColumn(int column){
    this.column = column;
}


public void setValue(String value){
    this.value = value;
}


public int getColumn(){
    return column;
}


public void setRow(int row){
    this.row = row;
}


public int getAnswerSetId(){
    return answerSetId;
}


public void setQuestionUid(String questionUid){
    this.questionUid = questionUid;
}


}