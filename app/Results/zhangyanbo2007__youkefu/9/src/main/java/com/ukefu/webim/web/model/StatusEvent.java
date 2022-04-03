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
import com.ukefu.util.event.UserEvent;
@Entity
@Table(name = "uk_callcenter_event")
@Proxy(lazy = false)
public class StatusEvent implements Comparable<StatusEvent>,Serializable,UserEvent{

 private  long serialVersionUID;

 private  String id;

 private  Date createtime;

 private  Date updatetime;

 private  boolean inside;

 private  String code;

 private  String creater;

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

 private  boolean forecast;

 private  String skill;

 private  String forecastid;

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

 private  boolean waste;

 private  boolean apstatus;

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

 private  String sipaddr;

 private  String extention;

 private  String hostid;

 private  String metaname;

 private  String taskid;

 private  String actid;

 private  String batid;

 private  String dataid;

 private  String nameid;

 private  String statustype;

 private  String disphonenum;

 private  String distype;

 private  String busstype;

 private  String siptrunk;

 private  boolean prefix;

 private  boolean callstatus;

 private  String qualitystatus;

 private  String qualitydisorgan;

 private  String qualitydisuser;

 private  Date qualitydistime;

 private  String assuser;

 private  String templateid;

 private  String qualitydistype;

 private  String qualityorgan;

 private  String qualityuser;

 private  int qualityscore;

 private  Date qualitytime;

 private  String qualitytype;

 private  String qualityactid;

 private  String qualityfilterid;

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

 private  boolean dtmf;

 private  String dtmfrec;

 private  int trans;

 private  Date transbegin;

 private  Date transend;

 private  String transtime;

 private  String transtatus;

 private  int transcost;

 private  String tranid;

 private  int qualitypass;

 private  String workstatus;

 private  String hangupcase;

 private  String hangupinitiator;

 private  String extno;

 private  String callresult;


public String getServiceid(){
    return serviceid;
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


public String getBatid(){
    return batid;
}


public void setContactsid(String contactsid){
    this.contactsid = contactsid;
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


public String getQualitydisuser(){
    return qualitydisuser;
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


public void setForecast(boolean forecast){
    this.forecast = forecast;
}


public void setQualitytype(String qualitytype){
    this.qualitytype = qualitytype;
}


public String getOtherlegdest(){
    return otherlegdest;
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


public void setQualitydisuser(String qualitydisuser){
    this.qualitydisuser = qualitydisuser;
}


public void setAnswertime(Date answertime){
    this.answertime = answertime;
}


public String getContactsid(){
    return contactsid;
}


public void setTransbegin(Date transbegin){
    this.transbegin = transbegin;
}


public boolean isCallstatus(){
    return callstatus;
}


public void setTranid(String tranid){
    this.tranid = tranid;
}


public void setWaste(boolean waste){
    this.waste = waste;
}


public void setName(String name){
    this.name = name;
}


public void setAction(String action){
    this.action = action;
}


public String getAssuser(){
    return assuser;
}


public void setDatestr(String datestr){
    this.datestr = datestr;
}


public void setCaller(String caller){
    this.caller = caller;
}


public void setAssuser(String assuser){
    this.assuser = assuser;
}


public void setDtmfrec(String dtmfrec){
    this.dtmfrec = dtmfrec;
}


public void setTranscost(int transcost){
    this.transcost = transcost;
}


public String getUsername(){
    return username;
}


public String getDtmfrec(){
    return dtmfrec;
}


public Date getCreatetime(){
    return createtime;
}


public String getHangupcase(){
    return hangupcase;
}


public String getAnswer(){
    return answer;
}


public void setSatisfdate(Date satisfdate){
    this.satisfdate = satisfdate;
}


public String getProvince(){
    return province;
}


public String getDatestr(){
    return datestr;
}


public boolean isInside(){
    return inside;
}


public String getUserid(){
    return userid;
}


public void setCalls(int calls){
    this.calls = calls;
}


public void setServiceid(String serviceid){
    this.serviceid = serviceid;
}


public boolean isMisscall(){
    return misscall;
}


public void setNameid(String nameid){
    this.nameid = nameid;
}


public void setQualitytime(Date qualitytime){
    this.qualitytime = qualitytime;
}


@Column(name = "srecord")
public boolean isRecord(){
    return record;
}


@Column(name = "scurrent")
public boolean isCurrent(){
    return current;
}


public String getDataid(){
    return dataid;
}


public void setPrefix(boolean prefix){
    this.prefix = prefix;
}


public void setCallstatus(boolean callstatus){
    this.callstatus = callstatus;
}


public void setHangupinitiator(String hangupinitiator){
    this.hangupinitiator = hangupinitiator;
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


public int getRecordtime(){
    return recordtime;
}


public void setCallback(boolean callback){
    this.callback = callback;
}


public String getLocaldatetime(){
    return localdatetime;
}


public String getMetaname(){
    return metaname;
}


public String getForecastid(){
    return forecastid;
}


public boolean isBridge(){
    return bridge;
}


public String getTranstime(){
    return transtime;
}


public void setExtno(String extno){
    this.extno = extno;
}


public String getQualityuser(){
    return qualityuser;
}


public Date getTransend(){
    return transend;
}


public void setQualityactid(String qualityactid){
    this.qualityactid = qualityactid;
}


public String getCity(){
    return city;
}


public void setCity(String city){
    this.city = city;
}


public void setTrans(int trans){
    this.trans = trans;
}


public String getBridgeid(){
    return bridgeid;
}


public void setCcquene(String ccquene){
    this.ccquene = ccquene;
}


public String getOrgan(){
    return organ;
}


public void setForecastid(String forecastid){
    this.forecastid = forecastid;
}


public void setHangupcase(String hangupcase){
    this.hangupcase = hangupcase;
}


public String getSource(){
    return source;
}


public String getSiptrunk(){
    return siptrunk;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setCalled(String called){
    this.called = called;
}


public boolean isApstatus(){
    return apstatus;
}


public void setSkill(String skill){
    this.skill = skill;
}


public String getTaskid(){
    return taskid;
}


public String getDirection(){
    return direction;
}


public long getTime(){
    return time;
}


public void setIsp(String isp){
    this.isp = isp;
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


public void setCallresult(String callresult){
    this.callresult = callresult;
}


public void setCurrent(boolean current){
    this.current = current;
}


public String getTranstatus(){
    return transtatus;
}


public int getQualitypass(){
    return qualitypass;
}


public String getTouser(){
    return touser;
}


public void setRecordtime(int recordtime){
    this.recordtime = recordtime;
}


public void setDistype(String distype){
    this.distype = distype;
}


public void setEndtime(Date endtime){
    this.endtime = endtime;
}


public String getQualityorgan(){
    return qualityorgan;
}


public void setBusstype(String busstype){
    this.busstype = busstype;
}


public void setDiscaller(String discaller){
    this.discaller = discaller;
}


public void setTranstatus(String transtatus){
    this.transtatus = transtatus;
}


public String getTemplateid(){
    return templateid;
}


public String getHourstr(){
    return hourstr;
}


public String getCallresult(){
    return callresult;
}


public void setDiscalled(String discalled){
    this.discalled = discalled;
}


public void setState(String state){
    this.state = state;
}


public void setQualityscore(int qualityscore){
    this.qualityscore = qualityscore;
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


public String getTranid(){
    return tranid;
}


public void setChannelstatus(String channelstatus){
    this.channelstatus = channelstatus;
}


public String getExtention(){
    return extention;
}


public String getQualitytype(){
    return qualitytype;
}


public void setStarttime(Date starttime){
    this.starttime = starttime;
}


public void setTemplateid(String templateid){
    this.templateid = templateid;
}


public String getStatus(){
    return status;
}


public String getDisphonenum(){
    return disphonenum;
}


public void setApstatus(boolean apstatus){
    this.apstatus = apstatus;
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


public Date getQualitydistime(){
    return qualitydistime;
}


public void setSatisfaction(String satisfaction){
    this.satisfaction = satisfaction;
}


public void setEndrecord(Date endrecord){
    this.endrecord = endrecord;
}


public String getCode(){
    return code;
}


public void setQualitypass(int qualitypass){
    this.qualitypass = qualitypass;
}


public void setQualitydistime(Date qualitydistime){
    this.qualitydistime = qualitydistime;
}


public void setSipaddr(String sipaddr){
    this.sipaddr = sipaddr;
}


public void setCode(String code){
    this.code = code;
}


public String getHangupinitiator(){
    return hangupinitiator;
}


public int getQualityscore(){
    return qualityscore;
}


public String getSipaddr(){
    return sipaddr;
}


public String getBusstype(){
    return busstype;
}


public String getAni(){
    return ani;
}


public void setLocaldatetime(String localdatetime){
    this.localdatetime = localdatetime;
}


public String getStatustype(){
    return statustype;
}


public String getQualitystatus(){
    return qualitystatus;
}


public void setDataid(String dataid){
    this.dataid = dataid;
}


public void setOtherdir(String otherdir){
    this.otherdir = otherdir;
}


public void setInside(boolean inside){
    this.inside = inside;
}


public void setSiptrunk(String siptrunk){
    this.siptrunk = siptrunk;
}


public String getQualityactid(){
    return qualityactid;
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


public Date getEndrecord(){
    return endrecord;
}


public String getActid(){
    return actid;
}


public String getRecordfile(){
    return recordfile;
}


public void setMetaname(String metaname){
    this.metaname = metaname;
}


public boolean isSatisf(){
    return satisf;
}


public String getHostid(){
    return hostid;
}


public void setTouser(String touser){
    this.touser = touser;
}


public Date getStartrecord(){
    return startrecord;
}


public void setActid(String actid){
    this.actid = actid;
}


public void setDirection(String direction){
    this.direction = direction;
}


public void setTransend(Date transend){
    this.transend = transend;
}


public void setCalldir(String calldir){
    this.calldir = calldir;
}


public void setUserid(String userid){
    this.userid = userid;
}


public void setHost(String host){
    this.host = host;
}


public void setExtention(String extention){
    this.extention = extention;
}


public void setQualityuser(String qualityuser){
    this.qualityuser = qualityuser;
}


public String getChannelstatus(){
    return channelstatus;
}


public String getWorkstatus(){
    return workstatus;
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


public String getVoicecalled(){
    return voicecalled;
}


public void setRecordfile(String recordfile){
    this.recordfile = recordfile;
}


public void setBridge(boolean bridge){
    this.bridge = bridge;
}


public String getTracesip(){
    return tracesip;
}


public void setMisscall(boolean misscall){
    this.misscall = misscall;
}


public String getQualitydisorgan(){
    return qualitydisorgan;
}


public String getCountry(){
    return country;
}


@Transient
public String getExtno(){
    return extno;
}


public String getDiscaller(){
    return discaller;
}


public String getNameid(){
    return nameid;
}


@Override
public int compareTo(StatusEvent o){
    return (int) (o.getTime() - this.getTime());
}


public String getQuene(){
    return quene;
}


public String getCalltype(){
    return calltype;
}


public Date getQualitytime(){
    return qualitytime;
}


public int getTranscost(){
    return transcost;
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


public void setVoicecalled(String voicecalled){
    this.voicecalled = voicecalled;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setQualityfilterid(String qualityfilterid){
    this.qualityfilterid = qualityfilterid;
}


public void setTaskid(String taskid){
    this.taskid = taskid;
}


public void setQualityorgan(String qualityorgan){
    this.qualityorgan = qualityorgan;
}


public void setAgentype(String agentype){
    this.agentype = agentype;
}


public void setOrgan(String organ){
    this.organ = organ;
}


public boolean isWaste(){
    return waste;
}


public void setQualitydistype(String qualitydistype){
    this.qualitydistype = qualitydistype;
}


public void setTranstime(String transtime){
    this.transtime = transtime;
}


public String getCalldir(){
    return calldir;
}


public void setTracesip(String tracesip){
    this.tracesip = tracesip;
}


public String getQualitydistype(){
    return qualitydistype;
}


public String getDiscalled(){
    return discalled;
}


public String getSkill(){
    return skill;
}


public void setDuration(int duration){
    this.duration = duration;
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


public void setWorkstatus(String workstatus){
    this.workstatus = workstatus;
}


public void setRingduration(int ringduration){
    this.ringduration = ringduration;
}


public int getTrans(){
    return trans;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


public boolean isForecast(){
    return forecast;
}


public void setSatisf(boolean satisf){
    this.satisf = satisf;
}


public String getSatisfaction(){
    return satisfaction;
}


public boolean isDtmf(){
    return dtmf;
}


public void setHostid(String hostid){
    this.hostid = hostid;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getCreater(){
    return creater;
}


public void setHourstr(String hourstr){
    this.hourstr = hourstr;
}


public void setRecordfilename(String recordfilename){
    this.recordfilename = recordfilename;
}


public void setDtmf(boolean dtmf){
    this.dtmf = dtmf;
}


public String getCalled(){
    return called;
}


public void setUsername(String username){
    this.username = username;
}


public Date getTransbegin(){
    return transbegin;
}


public String getDistype(){
    return distype;
}


public void setQualitydisorgan(String qualitydisorgan){
    this.qualitydisorgan = qualitydisorgan;
}


public Date getStarttime(){
    return starttime;
}


public void setStatus(String status){
    this.status = status;
}


public void setCalling(String calling){
    this.calling = calling;
}


public String getState(){
    return state;
}


public void setQualitystatus(String qualitystatus){
    this.qualitystatus = qualitystatus;
}


public String getOrgi(){
    return orgi;
}


public boolean isCallback(){
    return callback;
}


public String getQualityfilterid(){
    return qualityfilterid;
}


public Date getEndtime(){
    return endtime;
}


public String getServicestatus(){
    return servicestatus;
}


}