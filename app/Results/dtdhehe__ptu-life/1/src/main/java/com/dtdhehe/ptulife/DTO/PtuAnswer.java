package com.dtdhehe.ptulife.DTO;
 import javax.persistence.Entity;
import javax.persistence.Id;
public class PtuAnswer {

 private  String answerId;

 private  String answerTitle;

 private  String answerDesc;

 private  String answerAuthor;

 private  String answerDate;

 private  String answerHot;

 private  String userId;

 private  String answerIcon;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";


public String getAnswerTitle(){
    return answerTitle;
}


public String getAnswerIcon(){
    return answerIcon;
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


public void setAnswerTitle(String answerTitle){
    this.answerTitle = answerTitle;
}


public String getAnswerId(){
    return answerId;
}


public String getAnswerAuthor(){
    return answerAuthor;
}


public String getUserId(){
    return userId;
}


public void setUserId(String userId){
    this.userId = userId;
}


public void setAnswerDate(String answerDate){
    this.answerDate = answerDate;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ answerId).concat("/setAnswerDate"))

.queryParam("answerDate",answerDate)
;
restTemplate.put(builder.toUriString(),null);
}


}