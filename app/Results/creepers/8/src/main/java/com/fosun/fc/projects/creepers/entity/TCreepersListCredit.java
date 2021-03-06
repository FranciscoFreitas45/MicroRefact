package com.fosun.fc.projects.creepers.entity;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "T_CREEPERS_LIST_CREDIT")
@NamedQuery(name = "TCreepersListCredit.findAll", query = "SELECT t FROM TCreepersListCredit t")
public class TCreepersListCredit implements Serializable{

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_LIST_CREDIT_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_LIST_CREDIT")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_LIST_CREDIT_ID_GENERATOR")
 private  long id;

@Column(name = "HTML_URL")
 private  String htmlUrl;

@Column(name = "IMAGE_URL")
 private  String imageUrl;

 private  String memo;

@Column(name = "MESSAGE_CODE")
 private  String messageCode;

 private  String password;

@Column(name = "USER_CODE")
 private  String userCode;

public TCreepersListCredit() {
}
public void setImageUrl(String imageUrl){
    this.imageUrl = imageUrl;
}


public void setPassword(String password){
    this.password = password;
}


public void setHtmlUrl(String htmlUrl){
    this.htmlUrl = htmlUrl;
}


public long getId(){
    return this.id;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getMemo(){
    return this.memo;
}


public String getPassword(){
    return this.password;
}


public void setUserCode(String userCode){
    this.userCode = userCode;
}


public String getHtmlUrl(){
    return this.htmlUrl;
}


public String getImageUrl(){
    return this.imageUrl;
}


public void setId(long id){
    this.id = id;
}


public String getMessageCode(){
    return this.messageCode;
}


public void setMessageCode(String messageCode){
    this.messageCode = messageCode;
}


public String getUserCode(){
    return this.userCode;
}


}