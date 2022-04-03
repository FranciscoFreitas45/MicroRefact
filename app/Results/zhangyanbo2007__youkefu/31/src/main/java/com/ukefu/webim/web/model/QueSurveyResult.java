package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_que_result")
@org.hibernate.annotations.Proxy(lazy = false)
public class QueSurveyResult {

 private  long serialVersionUID;

 private  String id;

 private  String eventid;

 private  String processid;

 private  String orgi;

 private  String creater;

 private  Date createtime;

 private  Date endtime;

 private  String busstype;

 private  int processtime;

 private  int asktimes;

 private  int answertimes;

 private  int answertime;

 private  int errortimes;

 private  int timeouttimes;

 private  int retimes;

 private  String actid;

 private  String batchid;

 private  String filterid;

 private  String formfilterid;

 private  String nameid;

 private  String mobile;

 private  String sumscore;

 private  String organ;

 private  int focustimes;

 private  String level;

 private  String discalled;

 private  String distype;


public void setFocustimes(int focustimes){
    this.focustimes = focustimes;
}


public void setFilterid(String filterid){
    this.filterid = filterid;
}


public void setRetimes(int retimes){
    this.retimes = retimes;
}


public void setProcessid(String processid){
    this.processid = processid;
}


public String getNameid(){
    return nameid;
}


public void setLevel(String level){
    this.level = level;
}


public void setAsktimes(int asktimes){
    this.asktimes = asktimes;
}


public String getBatchid(){
    return batchid;
}


public void setId(String id){
    this.id = id;
}


public int getTimeouttimes(){
    return timeouttimes;
}


public int getAsktimes(){
    return asktimes;
}


public String getSumscore(){
    return sumscore;
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


public String getBusstype(){
    return busstype;
}


public void setAnswertime(int answertime){
    this.answertime = answertime;
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


public String getProcessid(){
    return processid;
}


public void setCreater(String creater){
    this.creater = creater;
}


public String getEventid(){
    return eventid;
}


public String getDiscalled(){
    return discalled;
}


public void setEventid(String eventid){
    this.eventid = eventid;
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


public void setAnswertimes(int answertimes){
    this.answertimes = answertimes;
}


public void setErrortimes(int errortimes){
    this.errortimes = errortimes;
}


public void setFormfilterid(String formfilterid){
    this.formfilterid = formfilterid;
}


public Date getCreatetime(){
    return createtime;
}


public String getActid(){
    return actid;
}


public String getFilterid(){
    return filterid;
}


public void setActid(String actid){
    this.actid = actid;
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


public void setSumscore(String sumscore){
    this.sumscore = sumscore;
}


public void setNameid(String nameid){
    this.nameid = nameid;
}


public void setDistype(String distype){
    this.distype = distype;
}


public void setEndtime(Date endtime){
    this.endtime = endtime;
}


public String getDistype(){
    return distype;
}


public void setBusstype(String busstype){
    this.busstype = busstype;
}


public void setBatchid(String batchid){
    this.batchid = batchid;
}


public int getProcesstime(){
    return processtime;
}


public String getLevel(){
    return level;
}


public String getFormfilterid(){
    return formfilterid;
}


public void setDiscalled(String discalled){
    this.discalled = discalled;
}


public String getOrgi(){
    return orgi;
}


public String getMobile(){
    return mobile;
}


public int getAnswertimes(){
    return answertimes;
}


public int getRetimes(){
    return retimes;
}


public Date getEndtime(){
    return endtime;
}


public int getAnswertime(){
    return answertime;
}


}