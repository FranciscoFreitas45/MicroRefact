package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
@Entity
@Table(name = "uk_agentuser")
@Proxy(lazy = false)
public class AgentUserTask {

 private  String id;

 private  String userid;

 private  String orgi;

 private  int tokenum;

 private  String warnings;

 private  Date warningtime;

 private  Date logindate;

 private  Date servicetime;

 private  int agentreplyinterval;

 private  int agentreplytime;

 private  int avgreplyinterval;

 private  int avgreplytime;

 private  int agenttimeout;

 private  int agenttimeouttimes;

 private  boolean servicetimeout;

 private  int agentservicetimeout;

 private  int agentfrewords;

 private  int servicefrewords;

 private  int firstreplytime;

 private  int agentreplys;

 private  int userasks;

 private  Date lastmessage;

 private  Date lastgetmessage;

 private  String lastmsg;

 private  String status;

 private  Date waittingtimestart;

 private  Date reptime;

 private  String reptimes;


public void setTokenum(int tokenum){
    this.tokenum = tokenum;
}


public int getTokenum(){
    return tokenum;
}


public void setAgentreplyinterval(int agentreplyinterval){
    this.agentreplyinterval = agentreplyinterval;
}


public Date getWarningtime(){
    return warningtime;
}


public String getLastmsg(){
    return lastmsg;
}


public void setAgentfrewords(int agentfrewords){
    this.agentfrewords = agentfrewords;
}


public String getStatus(){
    return status;
}


public void setServicetimeout(boolean servicetimeout){
    this.servicetimeout = servicetimeout;
}


public void setAvgreplytime(int avgreplytime){
    this.avgreplytime = avgreplytime;
}


public int getServicefrewords(){
    return servicefrewords;
}


public void setAgenttimeout(int agenttimeout){
    this.agenttimeout = agenttimeout;
}


public String getReptimes(){
    return reptimes;
}


public void setId(String id){
    this.id = id;
}


public void setLastmsg(String lastmsg){
    this.lastmsg = lastmsg;
}


public void setServicetime(Date servicetime){
    this.servicetime = servicetime;
}


public void setWarnings(String warnings){
    this.warnings = warnings;
}


public Date getLogindate(){
    return logindate;
}


public void setLogindate(Date logindate){
    this.logindate = logindate;
}


public void setAgentreplys(int agentreplys){
    this.agentreplys = agentreplys;
}


public int getAgentservicetimeout(){
    return agentservicetimeout;
}


public int getUserasks(){
    return userasks;
}


public int getAgenttimeouttimes(){
    return agenttimeouttimes;
}


public void setWaittingtimestart(Date waittingtimestart){
    this.waittingtimestart = waittingtimestart;
}


public void setReptimes(String reptimes){
    this.reptimes = reptimes;
}


public Date getLastmessage(){
    return lastmessage;
}


public int getAgentreplys(){
    return agentreplys;
}


public void setServicefrewords(int servicefrewords){
    this.servicefrewords = servicefrewords;
}


public void setWarningtime(Date warningtime){
    this.warningtime = warningtime;
}


public Date getReptime(){
    return reptime;
}


public void setLastgetmessage(Date lastgetmessage){
    this.lastgetmessage = lastgetmessage;
}


public Date getWaittingtimestart(){
    return waittingtimestart;
}


public Date getServicetime(){
    return servicetime;
}


public Date getLastgetmessage(){
    return lastgetmessage;
}


public void setAgentservicetimeout(int agentservicetimeout){
    this.agentservicetimeout = agentservicetimeout;
}


public String getWarnings(){
    return warnings;
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


public void setAgenttimeouttimes(int agenttimeouttimes){
    this.agenttimeouttimes = agenttimeouttimes;
}


public void setUserasks(int userasks){
    this.userasks = userasks;
}


public void setLastmessage(Date lastmessage){
    this.lastmessage = lastmessage;
}


public void setAvgreplyinterval(int avgreplyinterval){
    this.avgreplyinterval = avgreplyinterval;
}


public void setReptime(Date reptime){
    this.reptime = reptime;
}


public int getAgentfrewords(){
    return agentfrewords;
}


public String getUserid(){
    return userid;
}


public int getAvgreplyinterval(){
    return avgreplyinterval;
}


public void setUserid(String userid){
    this.userid = userid;
}


public int getAgentreplyinterval(){
    return agentreplyinterval;
}


public int getAgenttimeout(){
    return agenttimeout;
}


public void setStatus(String status){
    this.status = status;
}


public void setAgentreplytime(int agentreplytime){
    this.agentreplytime = agentreplytime;
}


public int getAvgreplytime(){
    return avgreplytime;
}


public boolean isServicetimeout(){
    return servicetimeout;
}


public void setFirstreplytime(int firstreplytime){
    this.firstreplytime = firstreplytime;
}


public String getOrgi(){
    return orgi;
}


public int getAgentreplytime(){
    return agentreplytime;
}


public int getFirstreplytime(){
    return firstreplytime;
}


}