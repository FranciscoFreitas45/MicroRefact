package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_work_monitor")
@org.hibernate.annotations.Proxy(lazy = false)
public class WorkMonitor {

 private  long serialVersionUID;

 private  String id;

 private  String agent;

 private  String username;

 private  String agentno;

 private  String extno;

 private  boolean admin;

 private  boolean firsttime;

 private  int firsttimes;

 private  String name;

 private  String status;

 private  String code;

 private  String worktype;

 private  String orgi;

 private  String agentserviceid;

 private  String skill;

 private  String skillname;

 private  boolean busy;

 private  String workstatus;

 private  Date createtime;

 private  String ani;

 private  String called;

 private  String direction;

 private  Date callstarttime;

 private  Date callendtime;

 private  int duration;

 private  String serviceid;

 private  String datestr;

 private  String servicestatus;

 private  String userid;

 private  String organ;


public String getServiceid(){
    return serviceid;
}


public String getName(){
    return name;
}


public void setCallstarttime(Date callstarttime){
    this.callstarttime = callstarttime;
}


public void setServicestatus(String servicestatus){
    this.servicestatus = servicestatus;
}


public void setFirsttime(boolean firsttime){
    this.firsttime = firsttime;
}


public String getExtno(){
    return extno;
}


public String getStatus(){
    return status;
}


public String getSkillname(){
    return skillname;
}


public void setExtno(String extno){
    this.extno = extno;
}


public int getDuration(){
    return duration;
}


public void setAni(String ani){
    this.ani = ani;
}


public void setId(String id){
    this.id = id;
}


public String getCode(){
    return code;
}


public void setCode(String code){
    this.code = code;
}


public boolean isAdmin(){
    return admin;
}


public Date getCallstarttime(){
    return callstarttime;
}


public String getAni(){
    return ani;
}


public void setOrgan(String organ){
    this.organ = organ;
}


public void setBusy(boolean busy){
    this.busy = busy;
}


public String getOrgan(){
    return organ;
}


public void setCalled(String called){
    this.called = called;
}


public void setAdmin(boolean admin){
    this.admin = admin;
}


public void setSkill(String skill){
    this.skill = skill;
}


public String getWorktype(){
    return worktype;
}


public String getSkill(){
    return skill;
}


public void setName(String name){
    this.name = name;
}


public void setDuration(int duration){
    this.duration = duration;
}


public int getFirsttimes(){
    return firsttimes;
}


public String getDirection(){
    return direction;
}


public void setAgentno(String agentno){
    this.agentno = agentno;
}


public void setWorkstatus(String workstatus){
    this.workstatus = workstatus;
}


public void setDatestr(String datestr){
    this.datestr = datestr;
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


public boolean isFirsttime(){
    return firsttime;
}


public String getUsername(){
    return username;
}


public Date getCreatetime(){
    return createtime;
}


public String getDatestr(){
    return datestr;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public void setDirection(String direction){
    this.direction = direction;
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


public void setServiceid(String serviceid){
    this.serviceid = serviceid;
}


public void setSkillname(String skillname){
    this.skillname = skillname;
}


public boolean isBusy(){
    return busy;
}


public String getCalled(){
    return called;
}


public void setUsername(String username){
    this.username = username;
}


public String getWorkstatus(){
    return workstatus;
}


public void setCallendtime(Date callendtime){
    this.callendtime = callendtime;
}


public void setFirsttimes(int firsttimes){
    this.firsttimes = firsttimes;
}


public String getAgent(){
    return agent;
}


public void setStatus(String status){
    this.status = status;
}


public Date getCallendtime(){
    return callendtime;
}


public void setAgent(String agent){
    this.agent = agent;
}


public void setAgentserviceid(String agentserviceid){
    this.agentserviceid = agentserviceid;
}


public String getOrgi(){
    return orgi;
}


public void setWorktype(String worktype){
    this.worktype = worktype;
}


public String getServicestatus(){
    return servicestatus;
}


}