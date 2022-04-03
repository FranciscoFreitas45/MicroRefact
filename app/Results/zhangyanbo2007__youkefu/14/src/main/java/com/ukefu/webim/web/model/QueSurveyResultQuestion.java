package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_que_result_question")
@org.hibernate.annotations.Proxy(lazy = false)
public class QueSurveyResultQuestion {

 private  long serialVersionUID;

 private  String id;

 private  String eventid;

 private  String resultid;

 private  String processid;

 private  String questionid;

 private  String orgi;

 private  String creater;

 private  Date createtime;

 private  Date endtime;

 private  int processtime;

 private  int asktimes;

 private  int answertimes;

 private  int answertime;

 private  int errortimes;

 private  int timeouttimes;

 private  int retimes;

 private  String answer;

 private  String nameid;

 private  String mobile;

 private  int sumscore;

 private  int score;

 private  String organ;

 private  int focustimes;


public void setEventid(String eventid){
    this.eventid = eventid;
}


public void setFocustimes(int focustimes){
    this.focustimes = focustimes;
}


public void setRetimes(int retimes){
    this.retimes = retimes;
}


public void setQuestionid(String questionid){
    this.questionid = questionid;
}


public void setProcessid(String processid){
    this.processid = processid;
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


public String getNameid(){
    return nameid;
}


public void setAnswertimes(int answertimes){
    this.answertimes = answertimes;
}


public void setErrortimes(int errortimes){
    this.errortimes = errortimes;
}


public String getQuestionid(){
    return questionid;
}


public Date getCreatetime(){
    return createtime;
}


public void setAsktimes(int asktimes){
    this.asktimes = asktimes;
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


public int getErrortimes(){
    return errortimes;
}


public int getTimeouttimes(){
    return timeouttimes;
}


public void setSumscore(int sumscore){
    this.sumscore = sumscore;
}


public int getAsktimes(){
    return asktimes;
}


public int getSumscore(){
    return sumscore;
}


public void setNameid(String nameid){
    this.nameid = nameid;
}


public void setEndtime(Date endtime){
    this.endtime = endtime;
}


public void setProcesstime(int processtime){
    this.processtime = processtime;
}


public void setTimeouttimes(int timeouttimes){
    this.timeouttimes = timeouttimes;
}


public int getFocustimes(){
    return focustimes;
}


public void setAnswertime(int answertime){
    this.answertime = answertime;
}


public int getProcesstime(){
    return processtime;
}


public void setMobile(String mobile){
    this.mobile = mobile;
}


public void setOrgan(String organ){
    this.organ = organ;
}


public String getOrgan(){
    return organ;
}


public void setResultid(String resultid){
    this.resultid = resultid;
}


public String getProcessid(){
    return processid;
}


public String getOrgi(){
    return orgi;
}


public String getResultid(){
    return resultid;
}


public void setCreater(String creater){
    this.creater = creater;
}


public String getMobile(){
    return mobile;
}


public int getAnswertimes(){
    return answertimes;
}


@Override
public String toString(){
    return "QueSurveyResultQuestion [id=" + id + ", eventid=" + eventid + ", resultid=" + resultid + ", processid=" + processid + ", questionid=" + questionid + ", orgi=" + orgi + ", creater=" + creater + ", createtime=" + createtime + ", endtime=" + endtime + ", processtime=" + processtime + ", asktimes=" + asktimes + ", answertimes=" + answertimes + ", answertime=" + answertime + ", errortimes=" + errortimes + ", timeouttimes=" + timeouttimes + ", retimes=" + retimes + ", answer=" + answer + ", nameid=" + nameid + ", mobile=" + mobile + ", sumscore=" + sumscore + ", score=" + score + ", organ=" + organ + ", focustimes=" + focustimes + "]";
}


public int getRetimes(){
    return retimes;
}


public int getScore(){
    return score;
}


public Date getEndtime(){
    return endtime;
}


public String getEventid(){
    return eventid;
}


public int getAnswertime(){
    return answertime;
}


public void setScore(int score){
    this.score = score;
}


}