package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_que_survey_answer")
@org.hibernate.annotations.Proxy(lazy = false)
public class QueSurveyAnswer {

 private  long serialVersionUID;

 private  String id;

 private  String questionid;

 private  String questionname;

 private  String title;

 private  String answer;

 private  String queid;

 private  int answerscore;

 private  String orgi;

 private  String creater;

 private  Date createtime;

 private  Date updatetime;

 private  String processid;

 private  String correct;

 private  String hanguptype;

 private  String hangupmsg;

 private  String hangupvoice;

 private  String anstype;


public void setQueid(String queid){
    this.queid = queid;
}


public void setHangupvoice(String hangupvoice){
    this.hangupvoice = hangupvoice;
}


public void setQuestionid(String questionid){
    this.questionid = questionid;
}


public void setCorrect(String correct){
    this.correct = correct;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public void setProcessid(String processid){
    this.processid = processid;
}


public int getAnswerscore(){
    return answerscore;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public String getCorrect(){
    return correct;
}


public String getHangupvoice(){
    return hangupvoice;
}


public String getHanguptype(){
    return hanguptype;
}


public String getTitle(){
    return title;
}


public String getQuestionid(){
    return questionid;
}


public Date getCreatetime(){
    return createtime;
}


public String getAnswer(){
    return answer;
}


public void setAnswer(String answer){
    this.answer = answer;
}


public void setId(String id){
    this.id = id;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getQuestionname(){
    return questionname;
}


public void setHanguptype(String hanguptype){
    this.hanguptype = hanguptype;
}


public String getAnstype(){
    return anstype;
}


public void setHangupmsg(String hangupmsg){
    this.hangupmsg = hangupmsg;
}


public void setAnstype(String anstype){
    this.anstype = anstype;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setTitle(String title){
    this.title = title;
}


public String getQueid(){
    return queid;
}


public void setAnswerscore(int answerscore){
    this.answerscore = answerscore;
}


public void setQuestionname(String questionname){
    this.questionname = questionname;
}


public String getOrgi(){
    return orgi;
}


public String getProcessid(){
    return processid;
}


public void setCreater(String creater){
    this.creater = creater;
}


public String getHangupmsg(){
    return hangupmsg;
}


}