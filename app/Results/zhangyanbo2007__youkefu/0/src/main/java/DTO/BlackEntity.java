package DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
public class BlackEntity {

 private  long serialVersionUID;

 private  String id;

 private  String orgi;

 private  String userid;

 private  String contactid;

 private  String sessionid;

 private  Date createtime;

 private  int controltime;

 private  Date endtime;

 private  String agentuser;

 private  String channel;

 private  String creater;

 private  String agentid;

 private  String phone;

 private  String openid;

 private  String agentserviceid;

 private  String description;

 private  int times;

 private  int chattime;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";


public String getPhone(){
    return phone;
}


public String getOpenid(){
    return openid;
}


public String getAgentuser(){
    return agentuser;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


public String getAgentserviceid(){
    return agentserviceid;
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


public int getChattime(){
    return chattime;
}


public Date getCreatetime(){
    return createtime;
}


public String getChannel(){
    return channel;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getCreater(){
    return creater;
}


public String getSessionid(){
    return sessionid;
}


public int getControltime(){
    return controltime;
}


public String getUserid(){
    return userid;
}


public void setAgentid(String agentid){
    this.agentid = agentid;
}


public int getTimes(){
    return times;
}


public String getAgentid(){
    return agentid;
}


public void setEndtime(Date endtime){
    this.endtime = endtime;
}


public String getContactid(){
    return contactid;
}


public void setPhone(String phone){
    this.phone = phone;
}


public void setOpenid(String openid){
    this.openid = openid;
}


public String getOrgi(){
    return orgi;
}


public void setTimes(int times){
    this.times = times;
}


public Date getEndtime(){
    return endtime;
}


public void setControltime(int controltime){
    this.controltime = controltime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setControltime"))

.queryParam("controltime",controltime)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAgentuser(String agentuser){
    this.agentuser = agentuser;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAgentuser"))

.queryParam("agentuser",agentuser)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUserid(String userid){
    this.userid = userid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUserid"))

.queryParam("userid",userid)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCreater(String creater){
    this.creater = creater;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCreater"))

.queryParam("creater",creater)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSessionid(String sessionid){
    this.sessionid = sessionid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSessionid"))

.queryParam("sessionid",sessionid)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAgentserviceid(String agentserviceid){
    this.agentserviceid = agentserviceid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAgentserviceid"))

.queryParam("agentserviceid",agentserviceid)
;
restTemplate.put(builder.toUriString(),null);
}


public void setChannel(String channel){
    this.channel = channel;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setChannel"))

.queryParam("channel",channel)
;
restTemplate.put(builder.toUriString(),null);
}


}