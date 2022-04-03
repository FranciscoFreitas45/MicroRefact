package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_notice")
@org.hibernate.annotations.Proxy(lazy = false)
public class Notice {

 private  long serialVersionUID;

 private  String id;

 private  String title;

 private  String content;

 private  String summary;

 private  String tags;

 private  String keyword;

 private  Date createtime;

 private  Date updatetime;

 private  String creater;

 private  String orgi;

 private  String organ;

 private  String smsserver;

 private  String emailserver;

 private  String smstemplate;

 private  String emailtemplate;

 private  String type;


public void setContent(String content){
    this.content = content;
}


public String getContent(){
    return content;
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


public String getTags(){
    return tags;
}


public void setTags(String tags){
    this.tags = tags;
}


public String getSmstemplate(){
    return smstemplate;
}


public String getTitle(){
    return title;
}


public void setKeyword(String keyword){
    this.keyword = keyword;
}


public Date getCreatetime(){
    return createtime;
}


public void setSmsserver(String smsserver){
    this.smsserver = smsserver;
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


public String getSmsserver(){
    return smsserver;
}


public void setSummary(String summary){
    this.summary = summary;
}


public String getSummary(){
    return summary;
}


public void setSmstemplate(String smstemplate){
    this.smstemplate = smstemplate;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setTitle(String title){
    this.title = title;
}


public String getEmailserver(){
    return emailserver;
}


public void setType(String type){
    this.type = type;
}


public String getKeyword(){
    return keyword;
}


public void setOrgan(String organ){
    this.organ = organ;
}


public String getOrgan(){
    return organ;
}


public void setEmailserver(String emailserver){
    this.emailserver = emailserver;
}


public String getEmailtemplate(){
    return emailtemplate;
}


public String getType(){
    return type;
}


public String getOrgi(){
    return orgi;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setEmailtemplate(String emailtemplate){
    this.emailtemplate = emailtemplate;
}


}