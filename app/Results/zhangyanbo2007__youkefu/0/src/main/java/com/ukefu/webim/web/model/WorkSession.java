package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_work_session")
@org.hibernate.annotations.Proxy(lazy = false)
public class WorkSession {

 private  long serialVersionUID;

 private  String id;

 private  String agent;

 private  String username;

 private  String agentno;

 private  boolean admin;

 private  String status;

 private  String code;

 private  String worktype;

 private  String orgi;

 private  String skill;

 private  String skillname;

 private  Date createtime;

 private  int duration;

 private  String sessionid;

 private  String clientid;

 private  String ipaddr;

 private  String hostname;

 private  String datestr;

 private  String userid;

 private  String organ;

 private  boolean firsttime;

 private  int firsttimes;

 private  Date begintime;

 private  Date endtime;


public void setDuration(int duration){
    this.duration = duration;
}


public int getFirsttimes(){
    return firsttimes;
}


public void setAgentno(String agentno){
    this.agentno = agentno;
}


public void setHostname(String hostname){
    this.hostname = hostname;
}


public void setFirsttime(boolean firsttime){
    this.firsttime = firsttime;
}


public void setDatestr(String datestr){
    this.datestr = datestr;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


public Date getBegintime(){
    return begintime;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public boolean isFirsttime(){
    return firsttime;
}


public void setIpaddr(String ipaddr){
    this.ipaddr = ipaddr;
}


public String getStatus(){
    return status;
}


public String getSkillname(){
    return skillname;
}


public String getUsername(){
    return username;
}


public String getHostname(){
    return hostname;
}


public Date getCreatetime(){
    return createtime;
}


public int getDuration(){
    return duration;
}


public void setSessionid(String sessionid){
    this.sessionid = sessionid;
}


public String getIpaddr(){
    return ipaddr;
}


public void setId(String id){
    this.id = id;
}


public String getDatestr(){
    return datestr;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getSessionid(){
    return sessionid;
}


public String getCode(){
    return code;
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


public void setClientid(String clientid){
    this.clientid = clientid;
}


public void setSkillname(String skillname){
    this.skillname = skillname;
}


public String getClientid(){
    return clientid;
}


public void setUsername(String username){
    this.username = username;
}


public void setEndtime(Date endtime){
    this.endtime = endtime;
}


public void setCode(String code){
    this.code = code;
}


public void setBegintime(Date begintime){
    this.begintime = begintime;
}


public void setFirsttimes(int firsttimes){
    this.firsttimes = firsttimes;
}


public String getAgent(){
    return agent;
}


public boolean isAdmin(){
    return admin;
}


public void setStatus(String status){
    this.status = status;
}


public void setAgent(String agent){
    this.agent = agent;
}


public void setOrgan(String organ){
    this.organ = organ;
}


public String getOrgan(){
    return organ;
}


public void setWorktype(String worktype){
    this.worktype = worktype;
}


public String getOrgi(){
    return orgi;
}


public void setAdmin(boolean admin){
    this.admin = admin;
}


public String getWorktype(){
    return worktype;
}


public void setSkill(String skill){
    this.skill = skill;
}


public Date getEndtime(){
    return endtime;
}


public String getSkill(){
    return skill;
}


}