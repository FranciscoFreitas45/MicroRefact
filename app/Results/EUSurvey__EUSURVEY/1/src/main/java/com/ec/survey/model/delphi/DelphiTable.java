package com.ec.survey.model.delphi;
 import java.util.ArrayList;
import java.util.List;
public class DelphiTable {

 private  List<DelphiTableEntry> entries;

 private  boolean hasNewComments;

 private  int offset;

 private  DelphiQuestionType questionType;

 private  int total;

 private  boolean showExplanationBox;


public void setTotal(int total){
    this.total = total;
}


public boolean getShowExplanationBox(){
    return showExplanationBox;
}


public void setShowExplanationBox(boolean showExplanationBox){
    this.showExplanationBox = showExplanationBox;
}


public List<DelphiTableEntry> getEntries(){
    return entries;
}


public void setOffset(int offset){
    this.offset = offset;
}


public DelphiQuestionType getQuestionType(){
    return questionType;
}


public int getOffset(){
    return offset;
}


public void setHasNewComments(boolean hasNewComments){
    this.hasNewComments = hasNewComments;
}


public int getTotal(){
    return total;
}


public void setQuestionType(DelphiQuestionType questionType){
    this.questionType = questionType;
}


public boolean getHasNewComments(){
    return hasNewComments;
}


public void setEntries(List<DelphiTableEntry> entries){
    this.entries = entries;
}


}