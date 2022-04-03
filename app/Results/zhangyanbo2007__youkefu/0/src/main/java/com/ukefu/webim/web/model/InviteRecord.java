package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.ukefu.core.UKDataContext;
@Entity
@Table(name = "uk_inviterecord")
@org.hibernate.annotations.Proxy(lazy = false)
public class InviteRecord {

 private  String id;

 private  String orgi;

 private  String userid;

 private  String title;

 private  String traceid;

 private  String url;

 private  String appid;

 private  String agentno;

 private  Date createtime;

 private  Date updatetime;

 private  String result;

 private  int responsetime;


public void setTraceid(String traceid){
    this.traceid = traceid;
}


public void setResponsetime(int responsetime){
    this.responsetime = responsetime;
}


public void setAgentno(String agentno){
    this.agentno = agentno;
}


public void setResult(String result){
    this.result = result;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getTraceid(){
    return traceid;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


public void setTitle(String title){
    this.title = title;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public int getResponsetime(){
    return responsetime;
}


public void setAppid(String appid){
    this.appid = appid;
}


public String getAppid(){
    return appid;
}


public void setUrl(String url){
    this.url = url;
}


public String getUrl(){
    return url;
}


public String getTitle(){
    return title;
}


public Date getCreatetime(){
    return createtime;
}


public String getResult(){
    return result;
}


public String getOrgi(){
    return orgi;
}


public void setId(String id){
    this.id = id;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getUserid(){
    return userid;
}


public void setUserid(String userid){
    this.userid = userid;
}


public String getAgentno(){
    return agentno;
}


}