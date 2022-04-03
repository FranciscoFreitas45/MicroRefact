package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_call_monitor")
@org.hibernate.annotations.Proxy(lazy = false)
public class CallMonitor {

 private  long serialVersionUID;

 private  String id;

 private  String agent;

 private  String username;

 private  String agentno;

 private  String name;

 private  String status;

 private  String code;

 private  String orgi;

 private  String agentserviceid;

 private  String skill;

 private  String skillname;

 private  String busy;

 private  Date createtime;

 private  String ani;

 private  String called;

 private  String direction;

 private  Date callstarttime;

 private  Date callendtime;

 private  int ringduration;

 private  int duration;

 private  int misscall;

 private  int record;

 private  int recordtime;

 private  String startrecord;

 private  String endrecord;

 private  String recordfilename;

 private  String recordfile;

 private  String source;

 private  Date answertime;

 private  int current;

 private  int init;

 private  String action;

 private  String host;

 private  String ipaddr;

 private  String servicesummary;

 private  String serviceid;

 private  String servicestatus;

 private  String channelstatus;

 private  String country;

 private  String province;

 private  String city;

 private  String isp;

 private  String contactsid;

 private  String extention;

 private  String hostid;

 private  String calltype;

 private  String calldir;

 private  String otherdir;

 private  String bridgeid;

 private  String bridre;

 private  String discaller;

 private  String discalled;

 private  String eventid;

 private  String userid;

 private  String organ;


public String getServiceid(){
    return serviceid;
}


public void setChannelstatus(String channelstatus){
    this.channelstatus = channelstatus;
}


public String getExtention(){
    return extention;
}


public void setProvince(String province){
    this.province = province;
}


public int getInit(){
    return init;
}


public void setServicestatus(String servicestatus){
    this.servicestatus = servicestatus;
}


public void setContactsid(String contactsid){
    this.contactsid = contactsid;
}


public String getStatus(){
    return status;
}


public int getRingduration(){
    return ringduration;
}


public String getBusy(){
    return busy;
}


public int getDuration(){
    return duration;
}


public String getIpaddr(){
    return ipaddr;
}


public void setAni(String ani){
    this.ani = ani;
}


public int getRecord(){
    return record;
}


public String getCode(){
    return code;
}


public void setEndrecord(String endrecord){
    this.endrecord = endrecord;
}


public String getBridre(){
    return bridre;
}


public void setBridre(String bridre){
    this.bridre = bridre;
}


public void setCode(String code){
    this.code = code;
}


public Date getCallstarttime(){
    return callstarttime;
}


public String getAni(){
    return ani;
}


public void setAnswertime(Date answertime){
    this.answertime = answertime;
}


public void setBusy(String busy){
    this.busy = busy;
}


public String getContactsid(){
    return contactsid;
}


public int getMisscall(){
    return misscall;
}


public void setOtherdir(String otherdir){
    this.otherdir = otherdir;
}


public String getEventid(){
    return eventid;
}


public void setName(String name){
    this.name = name;
}


public void setEventid(String eventid){
    this.eventid = eventid;
}


public void setAction(String action){
    this.action = action;
}


public void setServicesummary(String servicesummary){
    this.servicesummary = servicesummary;
}


public String getAgentserviceid(){
    return agentserviceid;
}


public void setIpaddr(String ipaddr){
    this.ipaddr = ipaddr;
}


public void setCalltype(String calltype){
    this.calltype = calltype;
}


public String getUsername(){
    return username;
}


public String getEndrecord(){
    return endrecord;
}


public Date getCreatetime(){
    return createtime;
}


public String getRecordfile(){
    return recordfile;
}


public String getProvince(){
    return province;
}


public String getHostid(){
    return hostid;
}


public String getStartrecord(){
    return startrecord;
}


public void setDirection(String direction){
    this.direction = direction;
}


public String getUserid(){
    return userid;
}


public void setCalldir(String calldir){
    this.calldir = calldir;
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


public void setHost(String host){
    this.host = host;
}


public void setExtention(String extention){
    this.extention = extention;
}


public String getServicesummary(){
    return servicesummary;
}


public String getChannelstatus(){
    return channelstatus;
}


public String getAction(){
    return action;
}


public String getAgent(){
    return agent;
}


public void setAgent(String agent){
    this.agent = agent;
}


public void setAgentserviceid(String agentserviceid){
    this.agentserviceid = agentserviceid;
}


public void setRecordfile(String recordfile){
    this.recordfile = recordfile;
}


public void setMisscall(int misscall){
    this.misscall = misscall;
}


public void setStartrecord(String startrecord){
    this.startrecord = startrecord;
}


public void setCountry(String country){
    this.country = country;
}


public String getName(){
    return name;
}


public void setCallstarttime(Date callstarttime){
    this.callstarttime = callstarttime;
}


public String getCountry(){
    return country;
}


public int getRecordtime(){
    return recordtime;
}


public String getDiscaller(){
    return discaller;
}


public String getSkillname(){
    return skillname;
}


public String getCalltype(){
    return calltype;
}


public void setId(String id){
    this.id = id;
}


public String getHost(){
    return host;
}


public String getCity(){
    return city;
}


public void setCity(String city){
    this.city = city;
}


public long getSerialversionuid(){
    return serialVersionUID;
}


public String getBridgeid(){
    return bridgeid;
}


public void setOrgan(String organ){
    this.organ = organ;
}


public String getOrgan(){
    return organ;
}


public String getSource(){
    return source;
}


public String getCalldir(){
    return calldir;
}


public void setCalled(String called){
    this.called = called;
}


public int getCurrent(){
    return current;
}


public void setSkill(String skill){
    this.skill = skill;
}


public String getSkill(){
    return skill;
}


public String getDiscalled(){
    return discalled;
}


public void setDuration(int duration){
    this.duration = duration;
}


public void setRecord(int record){
    this.record = record;
}


public void setSource(String source){
    this.source = source;
}


public String getRecordfilename(){
    return recordfilename;
}


public String getDirection(){
    return direction;
}


public void setAgentno(String agentno){
    this.agentno = agentno;
}


public void setRingduration(int ringduration){
    this.ringduration = ringduration;
}


public void setIsp(String isp){
    this.isp = isp;
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


public void setBridgeid(String bridgeid){
    this.bridgeid = bridgeid;
}


public void setHostid(String hostid){
    this.hostid = hostid;
}


public String getIsp(){
    return isp;
}


public void setInit(int init){
    this.init = init;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public void setCurrent(int current){
    this.current = current;
}


public void setRecordfilename(String recordfilename){
    this.recordfilename = recordfilename;
}


public String getAgentno(){
    return agentno;
}


public void setRecordtime(int recordtime){
    this.recordtime = recordtime;
}


public String getCalled(){
    return called;
}


public void setUsername(String username){
    this.username = username;
}


public void setCallendtime(Date callendtime){
    this.callendtime = callendtime;
}


public void setDiscaller(String discaller){
    this.discaller = discaller;
}


public void setStatus(String status){
    this.status = status;
}


public Date getCallendtime(){
    return callendtime;
}


public void setDiscalled(String discalled){
    this.discalled = discalled;
}


public String getOrgi(){
    return orgi;
}


public String getServicestatus(){
    return servicestatus;
}


public Date getAnswertime(){
    return answertime;
}


public String getOtherdir(){
    return otherdir;
}


}