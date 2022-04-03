package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_que_result_answer")
@org.hibernate.annotations.Proxy(lazy = false)
public class QueSurveyResultAnswer {

 private  long serialVersionUID;

 private  String id;

 private  String resultid;

 private  String processid;

 private  String questionid;

 private  int quetype;

 private  String answerid;

 private  String answer;

 private  int answerscore;

 private  String correct;

 private  String orgi;

 private  String creater;

 private  Date createtime;

 private  int answertime;

 private  String anstatus;


public void setQuestionid(String questionid){
    this.questionid = questionid;
}


public void setCorrect(String correct){
    this.correct = correct;
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


public void setAnswerid(String answerid){
    this.answerid = answerid;
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


public String getAnswerid(){
    return answerid;
}


public int getQuetype(){
    return quetype;
}


public void setAnswerscore(int answerscore){
    this.answerscore = answerscore;
}


public void setAnswertime(int answertime){
    this.answertime = answertime;
}


public void setQuetype(int quetype){
    this.quetype = quetype;
}


public String getAnstatus(){
    return anstatus;
}


public void setResultid(String resultid){
    this.resultid = resultid;
}


public String getResultid(){
    return resultid;
}


public String getProcessid(){
    return processid;
}


public String getOrgi(){
    return orgi;
}


public void setCreater(String creater){
    this.creater = creater;
}


public int getAnswertime(){
    return answertime;
}


public void setAnstatus(String anstatus){
    this.anstatus = anstatus;
}


}