package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_que_result_point")
@org.hibernate.annotations.Proxy(lazy = false)
public class QueSurveyResultPoint {

 private  long serialVersionUID;

 private  String id;

 private  String eventid;

 private  String resultid;

 private  String resultqueid;

 private  String processid;

 private  String questionid;

 private  String pointid;

 private  String answer;

 private  int answertime;

 private  String nameid;

 private  String orgi;

 private  String creater;

 private  Date createtime;

 private  String pointname;

 private  String pointtype;

 private  String focusword;

 private  Integer mincalltime;

 private  Integer maxcalltime;

 private  int score;


public void setEventid(String eventid){
    this.eventid = eventid;
}


public void setQuestionid(String questionid){
    this.questionid = questionid;
}


public void setFocusword(String focusword){
    this.focusword = focusword;
}


public void setMaxcalltime(Integer maxcalltime){
    this.maxcalltime = maxcalltime;
}


public Integer getMincalltime(){
    return mincalltime;
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


public String getQuestionid(){
    return questionid;
}


public Date getCreatetime(){
    return createtime;
}


public void setPointname(String pointname){
    this.pointname = pointname;
}


public String getAnswer(){
    return answer;
}


public void setAnswer(String answer){
    this.answer = answer;
}


public void setPointtype(String pointtype){
    this.pointtype = pointtype;
}


public void setId(String id){
    this.id = id;
}


public String getPointid(){
    return pointid;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public void setMincalltime(Integer mincalltime){
    this.mincalltime = mincalltime;
}


public String getPointname(){
    return pointname;
}


public void setNameid(String nameid){
    this.nameid = nameid;
}


public String getPointtype(){
    return pointtype;
}


public void setResultqueid(String resultqueid){
    this.resultqueid = resultqueid;
}


public void setAnswertime(int answertime){
    this.answertime = answertime;
}


public void setResultid(String resultid){
    this.resultid = resultid;
}


public void setPointid(String pointid){
    this.pointid = pointid;
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


public String getResultqueid(){
    return resultqueid;
}


public void setCreater(String creater){
    this.creater = creater;
}


@Override
public String toString(){
    return "QueSurveyResultPoint [id=" + id + ", eventid=" + eventid + ", resultid=" + resultid + ", resultqueid=" + resultqueid + ", processid=" + processid + ", questionid=" + questionid + ", pointid=" + pointid + ", orgi=" + orgi + ", creater=" + creater + ", createtime=" + createtime + ", pointtype=" + pointtype + ", focusword=" + focusword + ", mincalltime=" + mincalltime + ", maxcalltime=" + maxcalltime + ", score=" + score + "]";
}


public Integer getMaxcalltime(){
    return maxcalltime;
}


public String getFocusword(){
    return focusword;
}


public int getScore(){
    return score;
}


public String getEventid(){
    return eventid;
}


public void setScore(int score){
    this.score = score;
}


public int getAnswertime(){
    return answertime;
}


}