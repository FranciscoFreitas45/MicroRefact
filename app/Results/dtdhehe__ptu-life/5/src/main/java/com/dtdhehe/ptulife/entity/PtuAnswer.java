package com.dtdhehe.ptulife.entity;
 import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class PtuAnswer {

@Id
 private  String answerId;

 private  String answerTitle;

 private  String answerDesc;

 private  String answerAuthor;

 private  String answerDate;

 private  String answerHot;

 private  String userId;

 private  String answerIcon;


public String getAnswerTitle(){
    return answerTitle;
}


public void setAnswerDate(String answerDate){
    this.answerDate = answerDate;
}


public String getAnswerIcon(){
    return answerIcon;
}


public void setAnswerAuthor(String answerAuthor){
    this.answerAuthor = answerAuthor;
}


public String getAnswerHot(){
    return answerHot;
}


public String getAnswerDate(){
    return answerDate;
}


public String getAnswerDesc(){
    return answerDesc;
}


public void setAnswerId(String answerId){
    this.answerId = answerId;
}


public void setAnswerTitle(String answerTitle){
    this.answerTitle = answerTitle;
}


public void setAnswerIcon(String answerIcon){
    this.answerIcon = answerIcon;
}


public String getAnswerId(){
    return answerId;
}


public void setAnswerDesc(String answerDesc){
    this.answerDesc = answerDesc;
}


public String getAnswerAuthor(){
    return answerAuthor;
}


public String getUserId(){
    return userId;
}


public void setAnswerHot(String answerHot){
    this.answerHot = answerHot;
}


public void setUserId(String userId){
    this.userId = userId;
}


}