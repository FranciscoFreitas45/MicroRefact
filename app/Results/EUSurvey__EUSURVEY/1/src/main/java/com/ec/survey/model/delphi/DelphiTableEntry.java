package com.ec.survey.model.delphi;
 import com.ec.survey.tools.ConversionTools;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class DelphiTableEntry {

 private  List<DelphiTableAnswer> answers;

 private  String explanation;

 private  String updateString;

 private  int answerSetId;

 private  String answerSetUniqueCode;

 private  List<DelphiComment> comments;

 private  List<DelphiTableFile> files;


public String getAnswerSetUniqueCode(){
    return answerSetUniqueCode;
}


public void setAnswerSetUniqueCode(String answerSetUniqueCode){
    this.answerSetUniqueCode = answerSetUniqueCode;
}


public void setUpdateDate(Date update){
    this.updateString = ConversionTools.getFullString(update);
}


public List<DelphiTableAnswer> getAnswers(){
    return answers;
}


public void setExplanation(String explanation){
    this.explanation = explanation;
}


public void setUpdate(String updateString){
    this.updateString = updateString;
}


public void setAnswerSetId(int answerSetId){
    this.answerSetId = answerSetId;
}


public String getUpdate(){
    return updateString;
}


public List<DelphiComment> getComments(){
    return comments;
}


public int getAnswerSetId(){
    return answerSetId;
}


public List<DelphiTableFile> getFiles(){
    return files;
}


public String getExplanation(){
    return explanation;
}


}