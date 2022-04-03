package com.ukefu.webim.web.model;
 import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
@Entity
@Table(name = "uk_servicesummary")
@Proxy(lazy = false)
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


public String getProcessmemo(){
    return processmemo;
}


public void setChannel(String channel){
    this.channel = channel;
}


public boolean isReservation(){
    return reservation;
}


public void setContactsid(String contactsid){
    this.contactsid = contactsid;
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


public void setAni(String ani){
    this.ani = ani;
}


public void setServicetime(Date servicetime){
    this.servicetime = servicetime;
}


public void setId(String id){
    this.id = id;
}


public void setReservation(boolean reservation){
    this.reservation = reservation;
}


public Date getLogindate(){
    return logindate;
}


public void setLogindate(Date logindate){
    this.logindate = logindate;
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


public void setStatuseventid(String statuseventid){
    this.statuseventid = statuseventid;
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


public void setEmail(String email){
    this.email = email;
}


public String getEmail(){
    return email;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setCalled(String called){
    this.called = called;
}


public void setTimes(long times){
    this.times = times;
}


public void setProcessmemo(String processmemo){
    this.processmemo = processmemo;
}


public Date getServicetime(){
    return servicetime;
}


public void setAgentno(String agentno){
    this.agentno = agentno;
}


public String getStatuseventid(){
    return statuseventid;
}


public void setUpdateuser(String updateuser){
    this.updateuser = updateuser;
}


public void setCaller(String caller){
    this.caller = caller;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
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


public void setReservtime(Date reservtime){
    this.reservtime = reservtime;
}


public String getServicetype(){
    return servicetype;
}


public void setPhonenumber(String phonenumber){
    this.phonenumber = phonenumber;
}


public String getUsername(){
    return username;
}


public void setAgentusername(String agentusername){
    this.agentusername = agentusername;
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


public void setReservtype(String reservtype){
    this.reservtype = reservtype;
}


public String getPhonenumber(){
    return phonenumber;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getUserid(){
    return userid;
}


public String getAgentno(){
    return agentno;
}


public void setUserid(String userid){
    this.userid = userid;
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


public void setUsername(String username){
    this.username = username;
}


public void setProcess(boolean process){
    this.process = process;
}


public String getAgent(){
    return agent;
}


public void setStatus(String status){
    this.status = status;
}


public void setAgent(String agent){
    this.agent = agent;
}


public void setAgentserviceid(String agentserviceid){
    this.agentserviceid = agentserviceid;
}


public void setServicetype(String servicetype){
    this.servicetype = servicetype;
}


public String getOrgi(){
    return orgi;
}


}