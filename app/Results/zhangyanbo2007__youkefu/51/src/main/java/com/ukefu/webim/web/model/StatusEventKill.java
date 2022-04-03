package com.ukefu.webim.web.model;
 import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
import com.ukefu.core.UKDataContext;
import com.ukefu.util.UKTools;
@Entity
@Table(name = "uk_callcenter_event_kill")
@Proxy(lazy = false)
public class StatusEventKill implements Serializable,Comparable<StatusEventKill>{

 private  long serialVersionUID;

 private  String id;

 private  Date createtime;

 private  Date updatetime;

 private  boolean inside;

 private  String code;

 private  String source;

 private  String answer;

 private  boolean callback;

 private  String ccquene;

 private  String calltype;

 private  String voicecalled;

 private  String servicestatus;

 private  String channelstatus;

 private  Date answertime;

 private  int ringduration;

 private  boolean current;

 private  boolean init;

 private  String tracesip;

 private  String caller;

 private  String calling;

 private  String called;

 private  String discaller;

 private  String discalled;

 private  String agentype;

 private  String quene;

 private  String ani;

 private  String touser;

 private  String direction;

 private  String calldir;

 private  String otherdir;

 private  String otherlegdest;

 private  long time;

 private  String localdatetime;

 private  Date starttime;

 private  Date endtime;

 private  int duration;

 private  String status;

 private  String state;

 private  String agent;

 private  String action;

 private  String name;

 private  String host;

 private  String ipaddr;

 private  String extention;

 private  String hostid;

 private  String taskid;

 private  String actid;

 private  String batid;

 private  String dataid;

 private  String nameid;

 private  String statustype;

 private  String disphonenum;

 private  String distype;

 private  String siptrunk;

 private  boolean prefix;

 private  boolean record;

 private  Date startrecord;

 private  Date endrecord;

 private  int recordtime;

 private  String recordfile;

 private  String recordfilename;

 private  String contactsid;

 private  String bridgeid;

 private  boolean bridge;

 private  boolean misscall;

 private  boolean servicesummary;

 private  String serviceid;

 private  int calls;

 private  String orgi;

 private  String country;

 private  String province;

 private  String city;

 private  String isp;

 private  boolean satisf;

 private  String satisfaction;

 private  Date satisfdate;

 private  String datestr;

 private  String hourstr;

 private  String userid;

 private  String username;

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


public void setServicestatus(String servicestatus){
    this.servicestatus = servicestatus;
}


public void setDisphonenum(String disphonenum){
    this.disphonenum = disphonenum;
}


public void setStarttime(Date starttime){
    this.starttime = starttime;
}


public String getBatid(){
    return batid;
}


public void setContactsid(String contactsid){
    this.contactsid = contactsid;
}


public String getStatus(){
    return status;
}


public String getDisphonenum(){
    return disphonenum;
}


public int getRingduration(){
    return ringduration;
}


public String getCalling(){
    return calling;
}


public void setBatid(String batid){
    this.batid = batid;
}


public String getCaller(){
    return caller;
}


public void setStatustype(String statustype){
    this.statustype = statustype;
}


public String getAgentype(){
    return agentype;
}


public int getDuration(){
    return duration;
}


public String getIpaddr(){
    return ipaddr;
}


public void setSatisfaction(String satisfaction){
    this.satisfaction = satisfaction;
}


public void setAni(String ani){
    this.ani = ani;
}


public String getOtherlegdest(){
    return otherlegdest;
}


public void setEndrecord(Date endrecord){
    this.endrecord = endrecord;
}


public String getCode(){
    return code;
}


public void setTime(long time){
    this.time = time;
}


@Transient
public int getCalls(){
    return calls;
}


public boolean isServicesummary(){
    return servicesummary;
}


public void setCode(String code){
    this.code = code;
}


public String getAni(){
    return ani;
}


public void setAnswertime(Date answertime){
    this.answertime = answertime;
}


public String getContactsid(){
    return contactsid;
}


public void setLocaldatetime(String localdatetime){
    this.localdatetime = localdatetime;
}


public String getStatustype(){
    return statustype;
}


public void setDataid(String dataid){
    this.dataid = dataid;
}


public void setOtherdir(String otherdir){
    this.otherdir = otherdir;
}


public void setName(String name){
    this.name = name;
}


public void setAction(String action){
    this.action = action;
}


public void setInside(boolean inside){
    this.inside = inside;
}


public void setSiptrunk(String siptrunk){
    this.siptrunk = siptrunk;
}


public void setDatestr(String datestr){
    this.datestr = datestr;
}


public void setCaller(String caller){
    this.caller = caller;
}


public void setServicesummary(boolean servicesummary){
    this.servicesummary = servicesummary;
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


public Date getEndrecord(){
    return endrecord;
}


public Date getCreatetime(){
    return createtime;
}


public String getActid(){
    return actid;
}


public String getAnswer(){
    return answer;
}


public String getRecordfile(){
    return recordfile;
}


public boolean isSatisf(){
    return satisf;
}


public void setSatisfdate(Date satisfdate){
    this.satisfdate = satisfdate;
}


public String getProvince(){
    return province;
}


public String getHostid(){
    return hostid;
}


public void setTouser(String touser){
    this.touser = touser;
}


public String getDatestr(){
    return datestr;
}


public Date getStartrecord(){
    return startrecord;
}


public void setActid(String actid){
    this.actid = actid;
}


public boolean isInside(){
    return inside;
}


public void setDirection(String direction){
    this.direction = direction;
}


public String getUserid(){
    return userid;
}


public void setCalls(int calls){
    this.calls = calls;
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


public void setHost(String host){
    this.host = host;
}


public boolean isMisscall(){
    return misscall;
}


public void setExtention(String extention){
    this.extention = extention;
}


public void setNameid(String nameid){
    this.nameid = nameid;
}


public String getChannelstatus(){
    return channelstatus;
}


@Column(name = "srecord")
public boolean isRecord(){
    return record;
}


public String getAction(){
    return action;
}


public String getAgent(){
    return agent;
}


public void setQuene(String quene){
    this.quene = quene;
}


public void setAgent(String agent){
    this.agent = agent;
}


public void setOtherlegdest(String otherlegdest){
    this.otherlegdest = otherlegdest;
}


@Column(name = "scurrent")
public boolean isCurrent(){
    return current;
}


public String getDataid(){
    return dataid;
}


public String getVoicecalled(){
    return voicecalled;
}


public void setRecordfile(String recordfile){
    this.recordfile = recordfile;
}


public void setBridge(boolean bridge){
    this.bridge = bridge;
}


public void setPrefix(boolean prefix){
    this.prefix = prefix;
}


public String getTracesip(){
    return tracesip;
}


public void setMisscall(boolean misscall){
    this.misscall = misscall;
}


public void setStartrecord(Date startrecord){
    this.startrecord = startrecord;
}


public void setCountry(String country){
    this.country = country;
}


public String getName(){
    return name;
}


public String getCountry(){
    return country;
}


public int getRecordtime(){
    return recordtime;
}


public void setCallback(boolean callback){
    this.callback = callback;
}


public String getDiscaller(){
    return discaller;
}


public String getLocaldatetime(){
    return localdatetime;
}


public String getNameid(){
    return nameid;
}


@Override
public int compareTo(StatusEventKill o){
    return (int) (o.getTime() - this.getTime());
}


public boolean isBridge(){
    return bridge;
}


public String getQuene(){
    return quene;
}


public String getCalltype(){
    return calltype;
}


public String getCcquene(){
    return ccquene;
}


public void setId(String id){
    this.id = id;
}


public boolean isPrefix(){
    return prefix;
}


public String getHost(){
    return host;
}


public String getCity(){
    return city;
}


public void setVoicecalled(String voicecalled){
    this.voicecalled = voicecalled;
}


public void setCity(String city){
    this.city = city;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getBridgeid(){
    return bridgeid;
}


public void setTaskid(String taskid){
    this.taskid = taskid;
}


public void setAgentype(String agentype){
    this.agentype = agentype;
}


public void setCcquene(String ccquene){
    this.ccquene = ccquene;
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


public String getSiptrunk(){
    return siptrunk;
}


public String getCalldir(){
    return calldir;
}


public void setCalled(String called){
    this.called = called;
}


public void setTracesip(String tracesip){
    this.tracesip = tracesip;
}


public String getDiscalled(){
    return discalled;
}


public void setDuration(int duration){
    this.duration = duration;
}


public String getTaskid(){
    return taskid;
}


public void setSource(String source){
    this.source = source;
}


public void setRecord(boolean record){
    this.record = record;
}


public String getRecordfilename(){
    return recordfilename;
}


public String getDirection(){
    return direction;
}


public long getTime(){
    return time;
}


public void setRingduration(int ringduration){
    this.ringduration = ringduration;
}


public void setIsp(String isp){
    this.isp = isp;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "assigned")
public String getId(){
    return id;
}


public void setBridgeid(String bridgeid){
    this.bridgeid = bridgeid;
}


public void setSatisf(boolean satisf){
    this.satisf = satisf;
}


public String getSatisfaction(){
    return satisfaction;
}


public void setHostid(String hostid){
    this.hostid = hostid;
}


public void setAnswer(String answer){
    this.answer = answer;
}


public Date getSatisfdate(){
    return satisfdate;
}


public String getIsp(){
    return isp;
}


public void setInit(boolean init){
    this.init = init;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public void setCurrent(boolean current){
    this.current = current;
}


public void setHourstr(String hourstr){
    this.hourstr = hourstr;
}


public void setRecordfilename(String recordfilename){
    this.recordfilename = recordfilename;
}


public String getTouser(){
    return touser;
}


public void setRecordtime(int recordtime){
    this.recordtime = recordtime;
}


public String getCalled(){
    return called;
}


public void setDistype(String distype){
    this.distype = distype;
}


public void setUsername(String username){
    this.username = username;
}


public void setEndtime(Date endtime){
    this.endtime = endtime;
}


public String getDistype(){
    return distype;
}


public Date getStarttime(){
    return starttime;
}


public void setDiscaller(String discaller){
    this.discaller = discaller;
}


public void setStatus(String status){
    this.status = status;
}


public void setCalling(String calling){
    this.calling = calling;
}


public String getHourstr(){
    return hourstr;
}


public String getState(){
    return state;
}


public void setDiscalled(String discalled){
    this.discalled = discalled;
}


public String getOrgi(){
    return orgi;
}


public void setState(String state){
    this.state = state;
}


public boolean isCallback(){
    return callback;
}


public Date getEndtime(){
    return endtime;
}


public String getServicestatus(){
    return servicestatus;
}


public boolean isInit(){
    return init;
}


public Date getAnswertime(){
    return answertime;
}


public String getOtherdir(){
    return otherdir;
}


}