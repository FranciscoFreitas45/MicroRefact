package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_ekm_knowledge_auth")
@org.hibernate.annotations.Proxy(lazy = false)
public class EkmKnowledgeTypeAuth {

 private  long serialVersionUID;

 private  String id;

 private  String userid;

 private  String knowledgetypeid;

 private  String knowledgebaseid;

 private  String auth;

 private  boolean view;

 private  boolean cover;

 private  String organ;

 private  String orgi;

 private  String creater;

 private  Date createtime;


public void setAuth(String auth){
    this.auth = auth;
}


public String getAuth(){
    return auth;
}


public boolean isView(){
    return view;
}


public void setCover(boolean cover){
    this.cover = cover;
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


public String getKnowledgetypeid(){
    return knowledgetypeid;
}


public void setKnowledgebaseid(String knowledgebaseid){
    this.knowledgebaseid = knowledgebaseid;
}


public void setOrgan(String organ){
    this.organ = organ;
}


public String getOrgan(){
    return organ;
}


public Date getCreatetime(){
    return createtime;
}


public void setView(boolean view){
    this.view = view;
}


public String getOrgi(){
    return orgi;
}


public void setId(String id){
    this.id = id;
}


public void setCreater(String creater){
    this.creater = creater;
}


public String getKnowledgebaseid(){
    return knowledgebaseid;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public void setKnowledgetypeid(String knowledgetypeid){
    this.knowledgetypeid = knowledgetypeid;
}


public boolean isCover(){
    return cover;
}


public String getUserid(){
    return userid;
}


public void setUserid(String userid){
    this.userid = userid;
}


}