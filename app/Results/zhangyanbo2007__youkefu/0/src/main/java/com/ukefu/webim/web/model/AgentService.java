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
@Table(name = "uk_agentservice")
@Proxy(lazy = false)
public class AgentService implements Serializable{

 private  long serialVersionUID;

 private  String agentusername;

 private  String agentno;

 private  String status;

 private  long times;

 private  Date servicetime;

 private  String orgi;

 private  String id;

 private  String username;

 private  String userid;

 private  String channel;

 private  Date logindate;

 private  int firstreplytime;

 private  boolean invite;

 private  Date invitedate;

 private  String servicekind;

 private  String sessionid;

 private  int queneindex;

 private  String source;

 private  Date endtime;

 private  String ipaddr;

 private  String owner;

 private  String osname;

 private  String browser;

 private  String nickname;

 protected  String city;

 protected  String province;

 protected  String country;

 protected  String headimgurl;

 private  String region;

 private  long sessiontimes;

 private  int waittingtime;

 private  int tokenum;

 private  Date createtime;

 private  String agent;

 private  String skill;

 private  String memo;

 private  String agentuserid;

 private  String createdate;

 private  Date updatetime;

 private  String appid;

 private  String sessiontype;

 private  String contactsid;

 private  boolean satisfaction;

 private  Date satistime;

 private  String satislevel;

 private  String satiscomment;

 private  int agentreplyinterval;

 private  int agentreplytime;

 private  int avgreplyinterval;

 private  int avgreplytime;

 private  int agentreplys;

 private  int userasks;

 private  boolean trans;

 private  String transmemo;

 private  Date transtime;

 private  String initiator;

 private  String endby;

 private  String aiid;

 private  boolean aiservice;

 private  boolean foragent;

 private  String solvestatus;

 private  boolean leavemsg;

 private  String leavemsgstatus;

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

 private  String name;

 private  String email;

 private  String phone;

 private  String resion;

 private  String contextid;

 private  String dataid;

 private  String agentserviceid;

 private  long ordertime;

 private  String snsuser;

@Transient
 private  Date lastmessage;

 private  Date waittingtimestart;

@Transient
 private  Date lastgetmessage;

@Transient
 private  String lastmsg;

@Transient
 private  boolean tip;

@Transient
 private  String agentservice;

@Transient
 private  boolean agentTip;

 private  int qualitypass;

@Transient
 private  boolean fromhis;

@Transient
 private  boolean online;

@Transient
 private  boolean disconnect;

 private  String agentskill;


public void setProvince(String province){
    this.province = province;
}


public void setAiservice(boolean aiservice){
    this.aiservice = aiservice;
}


@Transient
public String getLastmsg(){
    return this.lastmsg;
}


public void setContactsid(String contactsid){
    this.contactsid = contactsid;
}


public String getOwner(){
    return owner;
}


public void setOwner(String owner){
    this.owner = owner;
}


public String getQualitydisuser(){
    return qualitydisuser;
}


public void setWaittingtime(int waittingtime){
    this.waittingtime = waittingtime;
}


public void setSessionid(String sessionid){
    this.sessionid = sessionid;
}


public String getIpaddr(){
    return this.ipaddr;
}


public String getHeadimgurl(){
    return this.headimgurl;
}


public void setServicetime(Date servicetime){
    this.servicetime = servicetime;
}


public void setLastmsg(String lastmsg){
    this.lastmsg = lastmsg;
}


public void setQualitytype(String qualitytype){
    this.qualitytype = qualitytype;
}


public boolean isTrans(){
    return trans;
}


public void setQualitydisuser(String qualitydisuser){
    this.qualitydisuser = qualitydisuser;
}


public String getMemo(){
    return memo;
}


public String getContactsid(){
    return contactsid;
}


public void setSolvestatus(String solvestatus){
    this.solvestatus = solvestatus;
}


public void setNickname(String nickname){
    this.nickname = nickname;
}


public String getBrowser(){
    return this.browser;
}


@Transient
public Date getWaittingtimestart(){
    return this.waittingtimestart;
}


public void setName(String name){
    this.name = name;
}


@Transient
public Date getLastgetmessage(){
    return this.lastgetmessage;
}


@Transient
public long getOrdertime(){
    return this.ordertime;
}


public String getAssuser(){
    return assuser;
}


public boolean isAiservice(){
    return aiservice;
}


public void setAssuser(String assuser){
    this.assuser = assuser;
}


@Transient
public String getTopic(){
    return "/" + this.orgi + "/" + this.agentno;
}


public String getUsername(){
    return this.username;
}


public Date getCreatetime(){
    return this.createtime;
}


public String getProvince(){
    return this.province;
}


public void setBrowser(String browser){
    this.browser = browser;
}


public String getUserid(){
    return this.userid;
}


public void setQualitytime(Date qualitytime){
    this.qualitytime = qualitytime;
}


public void setSnsuser(String snsuser){
    this.snsuser = snsuser;
}


public String getOsname(){
    return this.osname;
}


@Transient
public boolean isDisconnect(){
    return this.disconnect;
}


public String getDataid(){
    return dataid;
}


public void setForagent(boolean foragent){
    this.foragent = foragent;
}


public boolean isLeavemsg(){
    return leavemsg;
}


public String getSnsuser(){
    return this.snsuser;
}


public void setHeadimgurl(String headimgurl){
    this.headimgurl = headimgurl;
}


public String getAgentuserid(){
    return agentuserid;
}


public String getServicekind(){
    return servicekind;
}


public void setTokenum(int tokenum){
    this.tokenum = tokenum;
}


public void setCountry(String country){
    this.country = country;
}


public void setAiid(String aiid){
    this.aiid = aiid;
}


public String getName(){
    return name;
}


public void setSessiontimes(long sessiontimes){
    this.sessiontimes = sessiontimes;
}


public void setSatiscomment(String satiscomment){
    this.satiscomment = satiscomment;
}


public void setAgentreplyinterval(int agentreplyinterval){
    this.agentreplyinterval = agentreplyinterval;
}


public Date getTranstime(){
    return transtime;
}


public void setAgentskill(String agentskill){
    this.agentskill = agentskill;
}


public String getQualityuser(){
    return qualityuser;
}


@Transient
public int getQueneindex(){
    return queneindex;
}


public void setDisconnect(boolean disconnect){
    this.disconnect = disconnect;
}


public void setAgentreplys(int agentreplys){
    this.agentreplys = agentreplys;
}


public void setQualityactid(String qualityactid){
    this.qualityactid = qualityactid;
}


public String getCity(){
    return this.city;
}


public long getTimes(){
    return this.times;
}


public void setOrdertime(long ordertime){
    this.ordertime = ordertime;
}


public void setOnline(boolean online){
    this.online = online;
}


public void setCreatedate(String createdate){
    this.createdate = createdate;
}


public void setCity(String city){
    this.city = city;
}


public void setTrans(boolean trans){
    this.trans = trans;
}


public boolean isSatisfaction(){
    return satisfaction;
}


@Transient
public Date getLastmessage(){
    return this.lastmessage;
}


public int getAgentreplys(){
    return agentreplys;
}


public void setQueneindex(int queneindex){
    this.queneindex = queneindex;
}


public void setEmail(String email){
    this.email = email;
}


public String getRegion(){
    return this.region;
}


public String getSource(){
    return this.source;
}


public String getAgentskill(){
    return this.agentskill;
}


public void setSkill(String skill){
    this.skill = skill;
}


public void setSatistime(Date satistime){
    this.satistime = satistime;
}


public String getAiid(){
    return aiid;
}


public void setAgentno(String agentno){
    this.agentno = agentno;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "assigned")
public String getId(){
    return this.id;
}


public void setAgentusername(String agentusername){
    this.agentusername = agentusername;
}


public void setLastmessage(Date lastmessage){
    this.lastmessage = lastmessage;
}


public void setAvgreplyinterval(int avgreplyinterval){
    this.avgreplyinterval = avgreplyinterval;
}


public void setTip(boolean tip){
    this.tip = tip;
}


public String getSessionid(){
    return sessionid;
}


public String getEndby(){
    return endby;
}


public int getQualitypass(){
    return qualitypass;
}


public void setEndtime(Date endtime){
    this.endtime = endtime;
}


public String getQualityorgan(){
    return qualityorgan;
}


@Transient
public boolean isAgentTip(){
    return this.agentTip;
}


@Transient
public boolean isTip(){
    return this.tip;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getTemplateid(){
    return templateid;
}


public void setResion(String resion){
    this.resion = resion;
}


public int getAvgreplytime(){
    return avgreplytime;
}


public void setFirstreplytime(int firstreplytime){
    this.firstreplytime = firstreplytime;
}


public int getFirstreplytime(){
    return firstreplytime;
}


public void setQualityscore(int qualityscore){
    this.qualityscore = qualityscore;
}


public String getTransmemo(){
    return transmemo;
}


public void setInitiator(String initiator){
    this.initiator = initiator;
}


public String getQualitytype(){
    return qualitytype;
}


public void setAgentuserid(String agentuserid){
    this.agentuserid = agentuserid;
}


public void setTemplateid(String templateid){
    this.templateid = templateid;
}


public void setChannel(String channel){
    this.channel = channel;
}


public String getStatus(){
    return this.status;
}


public String getSolvestatus(){
    return solvestatus;
}


public void setAppid(String appid){
    this.appid = appid;
}


public void setAvgreplytime(int avgreplytime){
    this.avgreplytime = avgreplytime;
}


public Date getQualitydistime(){
    return qualitydistime;
}


public String getSessiontype(){
    return this.sessiontype;
}


public String getChannel(){
    return this.channel;
}


public void setSatisfaction(boolean satisfaction){
    this.satisfaction = satisfaction;
}


@Transient
public boolean isFromhis(){
    return this.fromhis;
}


public Date getLogindate(){
    return this.logindate;
}


public void setLogindate(Date logindate){
    this.logindate = logindate;
}


public Date getSatistime(){
    return satistime;
}


public void setTransmemo(String transmemo){
    this.transmemo = transmemo;
}


public void setQualitypass(int qualitypass){
    this.qualitypass = qualitypass;
}


public int getUserasks(){
    return userasks;
}


public void setQualitydistime(Date qualitydistime){
    this.qualitydistime = qualitydistime;
}


public int getQualityscore(){
    return qualityscore;
}


public void setWaittingtimestart(Date waittingtimestart){
    this.waittingtimestart = waittingtimestart;
}


public void setFromhis(boolean fromhis){
    this.fromhis = fromhis;
}


public String getQualitystatus(){
    return qualitystatus;
}


public void setDataid(String dataid){
    this.dataid = dataid;
}


public String getResion(){
    return resion;
}


public Date getServicetime(){
    return this.servicetime;
}


public String getPhone(){
    return phone;
}


public String getCreatedate(){
    return createdate;
}


public String getQualityactid(){
    return qualityactid;
}


public void setContextid(String contextid){
    this.contextid = contextid;
}


public String getAgentserviceid(){
    return this.agentserviceid;
}


public void setIpaddr(String ipaddr){
    this.ipaddr = ipaddr;
}


public boolean isInvite(){
    return invite;
}


public void setOsname(String osname){
    this.osname = osname;
}


public void setUserasks(int userasks){
    this.userasks = userasks;
}


public void setServicekind(String servicekind){
    this.servicekind = servicekind;
}


public String getAgentusername(){
    return agentusername;
}


public void setUserid(String userid){
    this.userid = userid;
}


public void setRegion(String region){
    this.region = region;
}


public void setQualityuser(String qualityuser){
    this.qualityuser = qualityuser;
}


public String getAgent(){
    return agent;
}


public String getAppid(){
    return this.appid;
}


public void setAgent(String agent){
    this.agent = agent;
}


public void setAgentreplytime(int agentreplytime){
    this.agentreplytime = agentreplytime;
}


public void setAgentserviceid(String agentserviceid){
    this.agentserviceid = agentserviceid;
}


public boolean isForagent(){
    return foragent;
}


@Transient
public String getAgentservice(){
    return this.agentservice;
}


public void setLeavemsg(boolean leavemsg){
    this.leavemsg = leavemsg;
}


public int getAgentreplytime(){
    return agentreplytime;
}


public String getContextid(){
    return this.contextid;
}


public String getQualitydisorgan(){
    return qualitydisorgan;
}


public String getCountry(){
    return this.country;
}


public int getTokenum(){
    return this.tokenum;
}


public Date getQualitytime(){
    return qualitytime;
}


public void setId(String id){
    this.id = id;
}


public String getLeavemsgstatus(){
    return leavemsgstatus;
}


public Date getInvitedate(){
    return invitedate;
}


public Date getUpdatetime(){
    return this.updatetime;
}


public void setQualityfilterid(String qualityfilterid){
    this.qualityfilterid = qualityfilterid;
}


public void setPhone(String phone){
    this.phone = phone;
}


public void setQualityorgan(String qualityorgan){
    this.qualityorgan = qualityorgan;
}


public void setAgentservice(String agentservice){
    this.agentservice = agentservice;
}


public void setQualitydistype(String qualitydistype){
    this.qualitydistype = qualitydistype;
}


public void setInvite(boolean invite){
    this.invite = invite;
}


public void setTranstime(Date transtime){
    this.transtime = transtime;
}


public String getEmail(){
    return email;
}


public void setTimes(long times){
    this.times = times;
}


public void setLastgetmessage(Date lastgetmessage){
    this.lastgetmessage = lastgetmessage;
}


public String getQualitydistype(){
    return qualitydistype;
}


public String getSkill(){
    return skill;
}


public void setSource(String source){
    this.source = source;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public long getSessiontimes(){
    return this.sessiontimes;
}


public String getSatiscomment(){
    return satiscomment;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


@Transient
public boolean isOnline(){
    return this.online;
}


public void setSatislevel(String satislevel){
    this.satislevel = satislevel;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public int getAvgreplyinterval(){
    return avgreplyinterval;
}


public void setEndby(String endby){
    this.endby = endby;
}


public String getAgentno(){
    return this.agentno;
}


public int getAgentreplyinterval(){
    return agentreplyinterval;
}


public void setLeavemsgstatus(String leavemsgstatus){
    this.leavemsgstatus = leavemsgstatus;
}


public void setUsername(String username){
    this.username = username;
}


public void setQualitydisorgan(String qualitydisorgan){
    this.qualitydisorgan = qualitydisorgan;
}


public String getSatislevel(){
    return satislevel;
}


public void setInvitedate(Date invitedate){
    this.invitedate = invitedate;
}


public void setStatus(String status){
    this.status = status;
}


public String getInitiator(){
    return initiator;
}


public String getNickname(){
    return this.nickname;
}


public void setQualitystatus(String qualitystatus){
    this.qualitystatus = qualitystatus;
}


public String getOrgi(){
    return this.orgi;
}


public int getWaittingtime(){
    return this.waittingtime;
}


public void setAgentTip(boolean agentTip){
    this.agentTip = agentTip;
}


public String getQualityfilterid(){
    return qualityfilterid;
}


public Date getEndtime(){
    return this.endtime;
}


public void setSessiontype(String sessiontype){
    this.sessiontype = sessiontype;
}


}