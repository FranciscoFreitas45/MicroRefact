package com.ec.survey.model.delphi;
 import java.util.ArrayList;
import java.util.Collection;
public class DelphiSection {

 private  Collection<DelphiQuestion> questions;

 private  String title;

 private  int level;

 private  boolean hasDelphiQuestions;

 private  boolean hasDirectDelphiQuestions;


public int getLevel(){
    return level;
}


public String getTitle(){
    return title;
}


public Collection<DelphiQuestion> getQuestions(){
    return questions;
}


public void setTitle(String title){
    this.title = title;
}


public void setHasDelphiQuestions(boolean hasDelphiQuestions){
    this.hasDelphiQuestions = hasDelphiQuestions;
}


public boolean isHasDelphiQuestions(){
    return hasDelphiQuestions;
}


public boolean isHasDirectDelphiQuestions(){
    return hasDirectDelphiQuestions;
}


public void setLevel(int level){
    this.level = level;
}


public void setHasDirectDelphiQuestions(boolean hasDirectDelphiQuestions){
    this.hasDirectDelphiQuestions = hasDirectDelphiQuestions;
}


}