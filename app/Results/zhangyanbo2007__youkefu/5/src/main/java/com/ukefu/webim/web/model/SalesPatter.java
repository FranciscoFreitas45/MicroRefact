package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_spt_salespatter")
@org.hibernate.annotations.Proxy(lazy = false)
public class SalesPatter implements AiCallOutProcess{

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String type;

 private  String scene;

 private  String welword;

 private  String welvoice;

 private  String weltype;

 private  String endword;

 private  String endvoice;

 private  String endtype;

 private  String totalscore;

 private  String score;

 private  String memo;

 private  String orgi;

 private  String creater;

 private  Date createtime;

 private  String updater;

 private  Date updatetime;

 private  String prostatus;

 private  String sumscore;

 private  String description;

 private  String questionid;


public void setName(String name){
    this.name = name;
}


public void setUpdater(String updater){
    this.updater = updater;
}


public String getName(){
    return name;
}


public String getTotalscore(){
    return totalscore;
}


public void setWelvoice(String welvoice){
    this.welvoice = welvoice;
}


public void setProstatus(String prostatus){
    this.prostatus = prostatus;
}


public void setQuestionid(String questionid){
    this.questionid = questionid;
}


public String getUpdater(){
    return updater;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
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


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public void setWeltype(String weltype){
    this.weltype = weltype;
}


public String getEndtype(){
    return endtype;
}


public String getWelvoice(){
    return welvoice;
}


public void setEndtype(String endtype){
    this.endtype = endtype;
}


public Date getCreatetime(){
    return createtime;
}


public String getQuestionid(){
    return questionid;
}


public void setEndvoice(String endvoice){
    this.endvoice = endvoice;
}


public String getEndvoice(){
    return endvoice;
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


public void setSumscore(String sumscore){
    this.sumscore = sumscore;
}


public String getSumscore(){
    return sumscore;
}


public void setWelword(String welword){
    this.welword = welword;
}


public void setEndword(String endword){
    this.endword = endword;
}


public void setScene(String scene){
    this.scene = scene;
}


public String getWelword(){
    return welword;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getProstatus(){
    return prostatus;
}


public void setType(String type){
    this.type = type;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getMemo(){
    return memo;
}


public String getType(){
    return type;
}


public String getScene(){
    return scene;
}


public String getWeltype(){
    return weltype;
}


public String getOrgi(){
    return orgi;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setTotalscore(String totalscore){
    this.totalscore = totalscore;
}


public String getScore(){
    return score;
}


public void setScore(String score){
    this.score = score;
}


public String getEndword(){
    return endword;
}


}