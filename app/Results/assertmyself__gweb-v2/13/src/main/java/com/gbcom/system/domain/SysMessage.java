package com.gbcom.system.domain;
 import java.io.Serializable;
import java.util.Date;
public class SysMessage implements Serializable{

 private  Long id;

 private  Integer type;

 private  Integer status;

 private  String title;

 private  String content;

 private  Long creatorId;

 private  Long recipientId;

 private  Date createTime;


public void setContent(String content){
    this.content = content;
}


public Date getCreateTime(){
    return createTime;
}


public String getContent(){
    return content;
}


public void setTitle(String title){
    this.title = title;
}


public Long getId(){
    return id;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setType(Integer type){
    this.type = type;
}


public Integer getStatus(){
    return status;
}


public Long getCreatorId(){
    return creatorId;
}


public void setStatus(Integer status){
    this.status = status;
}


public String getTitle(){
    return title;
}


public Integer getType(){
    return type;
}


public void setCreatorId(Long creatorId){
    this.creatorId = creatorId;
}


public void setId(Long id){
    this.id = id;
}


public void setRecipientId(Long recipientId){
    this.recipientId = recipientId;
}


public Long getRecipientId(){
    return recipientId;
}


}