package net.shangtech.weixin.weixin.entity;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import net.shangtech.ssh.core.base.IBaseEntity;
@Entity
@Table(name = "auto_reply")
public class AutoReply extends IBaseEntity{

 private  long serialVersionUID;

@Column(name = "rule_name")
 private  String name;

@Column(name = "keywords")
 private  String keywords;

@Column(name = "reply_type")
 private  Integer type;

@Column(name = "reply_content")
 private  String content;

@Column(name = "create_time")
 private  Date createTime;

@Column(name = "sys_user_id")
 private  Integer sysUserId;


public void setName(String name){
    this.name = name;
}


public void setContent(String content){
    this.content = content;
}


public Date getCreateTime(){
    return createTime;
}


public String getName(){
    return name;
}


public Integer getType(){
    return type;
}


public String getContent(){
    return content;
}


public String getKeywords(){
    return keywords;
}


public void setSysUserId(Integer sysUserId){
    this.sysUserId = sysUserId;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public Integer getSysUserId(){
    return sysUserId;
}


public void setType(Integer type){
    this.type = type;
}


public void setKeywords(String keywords){
    this.keywords = keywords;
}


}