package com.ukefu.webim.web.model;
 import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
import com.ukefu.util.UKTools;
@Entity
@Table(name = "uk_agentuser")
@Proxy(lazy = false)
public class AgentUser implements Serializable,Comparable<AgentUser>{

 private  long serialVersionUID;

 private  String id;

 private  String username;

 private  String agentno;

 private  String userid;

 private  String channel;

 private  Date logindate;

 private  String source;

 private  Date endtime;

 private  String title;

 private  String url;

 private  String traceid;

 private  String owner;

 private  String ipaddr;

 private  String osname;

 private  String browser;

 private  String nickname;

 protected  String city;

 private  String sessionid;

 protected  String province;

 protected  String country;

 protected  String headimgurl;

 private  String region;

 private  long sessiontimes;

 private  int waittingtime;

 private  int tokenum;

 private  Date createtime;

 private  Date updatetime;

 private  String status;

 private  String appid;

 private  String sessiontype;

 private  String contextid;

 private  String agentserviceid;

 private  String orgi;

 private  long ordertime;

 private  String snsuser;

 private  Date lastmessage;

 private  Date servicetime;

 private  Date waittingtimestart;

 private  Date lastgetmessage;

 private  String lastmsg;

 private  String skill;

 private  String agent;

 private  String skillname;

 private  String name;

 private  String email;

 private  String phone;

 private  String resion;

@Transient
 private  boolean tip;

@Transient
 private  boolean agentTip;

@Transient
 private  boolean fromhis;

@Transient
 private  boolean online;

@Transient
 private  boolean disconnect;

@Transient
 private  String agentskill;

 private  String agentservice;

public AgentUser() {
}public AgentUser(String userid, String channel, String snsuser, String username, String orgi, String appid) {
    this.userid = userid;
    this.channel = channel;
    this.snsuser = snsuser;
    this.appid = appid;
    this.username = username;
    this.orgi = orgi;
}
public void setProvince(String province){
    this.province = province;
}


public String getLastmsg(){
    return this.lastmsg;
}


public void setChannel(String channel){
    this.channel = channel;
}


public String getStatus(){
    return this.status;
}


public String getOwner(){
    return owner;
}


public void setAppid(String appid){
    this.appid = appid;
}


public void setOwner(String owner){
    this.owner = owner;
}


@Transient
public String getSessiontype(){
    return this.sessiontype;
}


public void setWaittingtime(int waittingtime){
    this.waittingtime = waittingtime;
}


public void setSessionid(String sessionid){
    this.sessionid = sessionid;
}


public String getChannel(){
    return this.channel;
}


public String getIpaddr(){
    return this.ipaddr;
}


public String getHeadimgurl(){
    return this.headimgurl;
}


public void setLastmsg(String lastmsg){
    this.lastmsg = lastmsg;
}


public void setServicetime(Date servicetime){
    this.servicetime = servicetime;
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


public void setWaittingtimestart(Date waittingtimestart){
    this.waittingtimestart = waittingtimestart;
}


public void setFromhis(boolean fromhis){
    this.fromhis = fromhis;
}


public void setNickname(String nickname){
    this.nickname = nickname;
}


public String getResion(){
    return resion;
}


public String getBrowser(){
    return this.browser;
}


public Date getWaittingtimestart(){
    return this.waittingtimestart;
}


public Date getServicetime(){
    return this.servicetime;
}


public void setName(String name){
    this.name = name;
}


public String getPhone(){
    return phone;
}


public Date getLastgetmessage(){
    return this.lastgetmessage;
}


@Transient
public long getOrdertime(){
    return this.ordertime;
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


@Transient
public String getTopic(){
    return "/" + this.orgi + "/" + this.agentno;
}


public String getUsername(){
    return this.username;
}


public void setOsname(String osname){
    this.osname = osname;
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


public void setUserid(String userid){
    this.userid = userid;
}


public void setSkillname(String skillname){
    this.skillname = skillname;
}


public void setRegion(String region){
    this.region = region;
}


public void setSnsuser(String snsuser){
    this.snsuser = snsuser;
}


public String getAgent(){
    return agent;
}


public String getOsname(){
    return this.osname;
}


@Transient
public boolean isDisconnect(){
    return this.disconnect;
}


public String getAppid(){
    return this.appid;
}


public void setAgent(String agent){
    this.agent = agent;
}


public void setAgentserviceid(String agentserviceid){
    this.agentserviceid = agentserviceid;
}


public String getAgentservice(){
    return this.agentservice;
}


public String getSnsuser(){
    return this.snsuser;
}


public void setHeadimgurl(String headimgurl){
    this.headimgurl = headimgurl;
}


public String getContextid(){
    return this.contextid;
}


public void setTokenum(int tokenum){
    this.tokenum = tokenum;
}


public void setCountry(String country){
    this.country = country;
}


public String getName(){
    return name;
}


public String getCountry(){
    return this.country;
}


public void setSessiontimes(long sessiontimes){
    this.sessiontimes = sessiontimes;
}


public int getTokenum(){
    return this.tokenum;
}


@Override
public int compareTo(AgentUser o){
    int ret = 0;
    if (this.getLogindate() == null) {
        ret = -1;
    } else if (o.getLogindate() == null) {
        ret = 1;
    } else {
        if (this.getLogindate().after(o.getLogindate())) {
            ret = 1;
        } else {
            ret = -1;
        }
    }
    return ret;
}


@Transient
public String getSkillname(){
    return skillname;
}


public void setAgentskill(String agentskill){
    this.agentskill = agentskill;
}


public String getTitle(){
    return title;
}


public void setId(String id){
    this.id = id;
}


public void setDisconnect(boolean disconnect){
    this.disconnect = disconnect;
}


public String getCity(){
    return this.city;
}


public void setOrdertime(long ordertime){
    this.ordertime = ordertime;
}


public void setOnline(boolean online){
    this.online = online;
}


public void setCity(String city){
    this.city = city;
}


public Date getUpdatetime(){
    return this.updatetime;
}


public void setTitle(String title){
    this.title = title;
}


public void setPhone(String phone){
    this.phone = phone;
}


public Date getLastmessage(){
    return this.lastmessage;
}


public void setAgentservice(String agentservice){
    this.agentservice = agentservice;
}


public void setUrl(String url){
    this.url = url;
}


public String getUrl(){
    return url;
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


public String getEmail(){
    return email;
}


public void setLastgetmessage(Date lastgetmessage){
    this.lastgetmessage = lastgetmessage;
}


public void setSkill(String skill){
    this.skill = skill;
}


public String getSkill(){
    return skill;
}


public void setSource(String source){
    this.source = source;
}


public void setTraceid(String traceid){
    this.traceid = traceid;
}


public void setAgentno(String agentno){
    this.agentno = agentno;
}


public String getTraceid(){
    return traceid;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public long getSessiontimes(){
    return this.sessiontimes;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return this.id;
}


@Transient
public boolean isOnline(){
    return this.online;
}


public void setLastmessage(Date lastmessage){
    this.lastmessage = lastmessage;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public void setTip(boolean tip){
    this.tip = tip;
}


public String getSessionid(){
    return sessionid;
}


public String getAgentno(){
    return this.agentno;
}


public void setUsername(String username){
    this.username = username;
}


public void setEndtime(Date endtime){
    this.endtime = endtime;
}


@Transient
public boolean isAgentTip(){
    return this.agentTip;
}


@Transient
public boolean isTip(){
    return this.tip;
}


public void setStatus(String status){
    this.status = status;
}


public void setResion(String resion){
    this.resion = resion;
}


public String getNickname(){
    return this.nickname;
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


public Date getEndtime(){
    return this.endtime;
}


public void setSessiontype(String sessiontype){
    this.sessiontype = sessiontype;
}


}