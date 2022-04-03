package com.ec.survey.model.delphi;
 import java.util.ArrayList;
import java.util.Collection;
public class DelphiStructure {

 private  Collection<DelphiSection> sections;

 private  boolean unansweredMandatoryQuestions;


public void setUnansweredMandatoryQuestions(boolean unansweredMandatoryQuestions){
    this.unansweredMandatoryQuestions = unansweredMandatoryQuestions;
}


public Collection<DelphiSection> getSections(){
    return sections;
}


public boolean isUnansweredMandatoryQuestions(){
    return unansweredMandatoryQuestions;
}


}