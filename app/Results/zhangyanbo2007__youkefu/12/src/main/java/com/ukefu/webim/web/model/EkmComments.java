package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_ekm_comments")
@org.hibernate.annotations.Proxy(lazy = false)
public class EkmComments {

 private  long serialVersionUID;

 private  String id;

 private  String knowledgeid;

 private  String knowledgeower;

 private  String dismenid;

 private  String dismentypeid;

 private  String knowledgetypeid;

 private  String knowbaseid;

 private  String content;

 private  Date createtime;

 private  String creater;

 private  String organ;

 private  String orgi;

 private  boolean datastatus;

 private  double rate;

 private  String title;


public void setContent(String content){
    this.content = content;
}


public void setDismenid(String dismenid){
    this.dismenid = dismenid;
}


public String getContent(){
    return content;
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


public String getDismentypeid(){
    return dismentypeid;
}


public void setDatastatus(boolean datastatus){
    this.datastatus = datastatus;
}


public double getRate(){
    return rate;
}


public String getTitle(){
    return title;
}


public Date getCreatetime(){
    return createtime;
}


public void setKnowledgeower(String knowledgeower){
    this.knowledgeower = knowledgeower;
}


public void setKnowbaseid(String knowbaseid){
    this.knowbaseid = knowbaseid;
}


public void setId(String id){
    this.id = id;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getCreater(){
    return creater;
}


public String getKnowledgeid(){
    return knowledgeid;
}


public void setKnowledgetypeid(String knowledgetypeid){
    this.knowledgetypeid = knowledgetypeid;
}


public String getKnowbaseid(){
    return knowbaseid;
}


public void setTitle(String title){
    this.title = title;
}


public String getKnowledgetypeid(){
    return knowledgetypeid;
}


public String getKnowledgeower(){
    return knowledgeower;
}


public void setRate(double rate){
    this.rate = rate;
}


public void setKnowledgeid(String knowledgeid){
    this.knowledgeid = knowledgeid;
}


public void setOrgan(String organ){
    this.organ = organ;
}


public String getDismenid(){
    return dismenid;
}


public String getOrgan(){
    return organ;
}


public String getOrgi(){
    return orgi;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setDismentypeid(String dismentypeid){
    this.dismentypeid = dismentypeid;
}


public boolean isDatastatus(){
    return datastatus;
}


}