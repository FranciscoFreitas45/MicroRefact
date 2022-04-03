package DTO;
 import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
public class AgentServiceSummary implements Serializable{

 private  long serialVersionUID;

 private  String agentusername;

 private  String agentno;

 private  String status;

 private  long times;

 private  Date servicetime;

 private  Date createtime;

 private  String agentserviceid;

 private  String userid;

 private  String statuseventid;

 private  String contactsid;

 private  String orgi;

 private  String id;

 private  String creater;

 private  String username;

 private  String channel;

 private  Date logindate;

 private  String servicetype;

 private  boolean reservation;

 private  String reservtype;

 private  Date reservtime;

 private  String email;

 private  String phonenumber;

 private  String ani;

 private  String caller;

 private  String called;

 private  String agent;

 private  String summary;

 private  boolean process;

 private  String updateuser;

 private  Date updatetime;

 private  String processmemo;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public String getProcessmemo(){
    return processmemo;
}


public boolean isReservation(){
    return reservation;
}


public String getStatus(){
    return status;
}


public String getCaller(){
    return caller;
}


public String getChannel(){
    return channel;
}


public void setServicetime(Date servicetime){
    this.servicetime = servicetime;
}


public void setReservation(boolean reservation){
    this.reservation = reservation;
}


public Date getLogindate(){
    return logindate;
}


public boolean isProcess(){
    return process;
}


public long getTimes(){
    return times;
}


public Date getReservtime(){
    return reservtime;
}


public String getUpdateuser(){
    return updateuser;
}


public long getSerialversionuid(){
    return serialVersionUID;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getAni(){
    return ani;
}


public String getContactsid(){
    return contactsid;
}


public String getEmail(){
    return email;
}


public void setCalled(String called){
    this.called = called;
}


public void setProcessmemo(String processmemo){
    this.processmemo = processmemo;
}


public Date getServicetime(){
    return servicetime;
}


public String getStatuseventid(){
    return statuseventid;
}


public void setCaller(String caller){
    this.caller = caller;
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


public String getServicetype(){
    return servicetype;
}


public String getUsername(){
    return username;
}


public Date getCreatetime(){
    return createtime;
}


public String getAgentusername(){
    return agentusername;
}


public String getReservtype(){
    return reservtype;
}


public String getPhonenumber(){
    return phonenumber;
}


public String getCreater(){
    return creater;
}


public String getUserid(){
    return userid;
}


public String getAgentno(){
    return agentno;
}


public void setSummary(String summary){
    this.summary = summary;
}


public String getSummary(){
    return summary;
}


public String getCalled(){
    return called;
}


public void setProcess(boolean process){
    this.process = process;
}


public String getAgent(){
    return agent;
}


public void setAgent(String agent){
    this.agent = agent;
}


public void setServicetype(String servicetype){
    this.servicetype = servicetype;
}


public String getOrgi(){
    return orgi;
}


public void setCreater(String creater){
    this.creater = creater;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCreater"))

.queryParam("creater",creater)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCreatetime"))

.queryParam("createtime",createtime)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAgentno(String agentno){
    this.agentno = agentno;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAgentno"))

.queryParam("agentno",agentno)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUsername(String username){
    this.username = username;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUsername"))

.queryParam("username",username)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAgentusername(String agentusername){
    this.agentusername = agentusername;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAgentusername"))

.queryParam("agentusername",agentusername)
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


public void setLogindate(Date logindate){
    this.logindate = logindate;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setLogindate"))

.queryParam("logindate",logindate)
;
restTemplate.put(builder.toUriString(),null);
}


public void setContactsid(String contactsid){
    this.contactsid = contactsid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setContactsid"))

.queryParam("contactsid",contactsid)
;
restTemplate.put(builder.toUriString(),null);
}


public void setEmail(String email){
    this.email = email;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setEmail"))

.queryParam("email",email)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPhonenumber(String phonenumber){
    this.phonenumber = phonenumber;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setPhonenumber"))

.queryParam("phonenumber",phonenumber)
;
restTemplate.put(builder.toUriString(),null);
}


}