package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_ai_snsaccount")
@org.hibernate.annotations.Proxy(lazy = false)
public class AiSNSAccount {

 private  long serialVersionUID;

 private  String id;

 private  String aiid;

 private  String snsid;

 private  String creater;

 private  String orgi;

 private  Date createtime;


public void setAiid(String aiid){
    this.aiid = aiid;
}


public Date getCreatetime(){
    return createtime;
}


public String getAiid(){
    return aiid;
}


public void setSnsid(String snsid){
    this.snsid = snsid;
}


public String getOrgi(){
    return orgi;
}


public String getSnsid(){
    return snsid;
}


public void setId(String id){
    this.id = id;
}


public void setCreater(String creater){
    this.creater = creater;
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


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


}