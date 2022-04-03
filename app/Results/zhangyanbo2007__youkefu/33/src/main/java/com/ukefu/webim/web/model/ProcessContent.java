package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_bpm_process")
@org.hibernate.annotations.Proxy(lazy = false)
public class ProcessContent {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String code;

 private  Date createtime;

 private  String creater;

 private  String username;

 private  Date updatetime;

 private  String orgi;

 private  String content;

 private  String status;

 private  String title;

 private  String processid;

 private  String processtype;

 private  boolean published;


public void setName(String name){
    this.name = name;
}


public void setContent(String content){
    this.content = content;
}


public String getName(){
    return name;
}


public String getProcesstype(){
    return processtype;
}


public String getContent(){
    return content;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
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


public String getStatus(){
    return status;
}


public String getUsername(){
    return username;
}


public String getTitle(){
    return title;
}


public Date getCreatetime(){
    return createtime;
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


public String getCode(){
    return code;
}


public void setUsername(String username){
    this.username = username;
}


public boolean isPublished(){
    return published;
}


public void setCode(String code){
    this.code = code;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setTitle(String title){
    this.title = title;
}


public void setPublished(boolean published){
    this.published = published;
}


public void setStatus(String status){
    this.status = status;
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


public void setProcesstype(String processtype){
    this.processtype = processtype;
}


}