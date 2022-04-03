package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.ukefu.core.UKDataContext;
@Entity
@Table(name = "uk_callcenter_extention")
@org.hibernate.annotations.Proxy(lazy = false)
public class Extention {

 private  long serialVersionUID;

 private  String id;

 private  String extention;

 private  String hostid;

 private  String agentno;

 private  String password;

 private  String extype;

 private  String subtype;

 private  boolean callout;

 private  boolean playnum;

 private  boolean record;

 private  String description;

 private  String mediapath;

 private  String siptrunk;

 private  String enableai;

 private  String aiid;

 private  String sceneid;

 private  String welcomemsg;

 private  String waitmsg;

 private  String tipmessage;

 private  int waittime;

 private  int waittiptimes;

 private  String errormessage;

 private  boolean enablewebrtc;

 private  String bustype;

 private  String proid;

 private  String queid;

 private  String aitype;

 private  String orgi;

 private  String creater;

 private  Date createtime;

 private  Date updatetime;


public void setPassword(String password){
    this.password = password;
}


public boolean isCallout(){
    return callout;
}


public void setAiid(String aiid){
    this.aiid = aiid;
}


public String getExtention(){
    return extention;
}


public String getErrormessage(){
    return errormessage;
}


public void setWaittime(int waittime){
    this.waittime = waittime;
}


public String getTipmessage(){
    return tipmessage;
}


public int getWaittiptimes(){
    return waittiptimes;
}


public void setExtype(String extype){
    this.extype = extype;
}


public void setId(String id){
    this.id = id;
}


public void setEnableai(String enableai){
    this.enableai = enableai;
}


public void setCallout(boolean callout){
    this.callout = callout;
}


public String getSceneid(){
    return sceneid;
}


public String getExtype(){
    return extype;
}


public void setTipmessage(String tipmessage){
    this.tipmessage = tipmessage;
}


public void setMediapath(String mediapath){
    this.mediapath = mediapath;
}


public String getBustype(){
    return bustype;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getProid(){
    return proid;
}


public int getWaittime(){
    return waittime;
}


public void setAitype(String aitype){
    this.aitype = aitype;
}


public String getSiptrunk(){
    return siptrunk;
}


public String getSubtype(){
    return subtype;
}


public void setSceneid(String sceneid){
    this.sceneid = sceneid;
}


public boolean isEnablewebrtc(){
    return enablewebrtc;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setErrormessage(String errormessage){
    this.errormessage = errormessage;
}


public void setQueid(String queid){
    this.queid = queid;
}


public void setWaittiptimes(int waittiptimes){
    this.waittiptimes = waittiptimes;
}


public void setRecord(boolean record){
    this.record = record;
}


public void setPlaynum(boolean playnum){
    this.playnum = playnum;
}


public void setSiptrunk(String siptrunk){
    this.siptrunk = siptrunk;
}


public String getAitype(){
    return aitype;
}


public String getAiid(){
    return aiid;
}


public void setAgentno(String agentno){
    this.agentno = agentno;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public void setWelcomemsg(String welcomemsg){
    this.welcomemsg = welcomemsg;
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


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public void setProid(String proid){
    this.proid = proid;
}


public boolean isPlaynum(){
    return playnum;
}


public String getWelcomemsg(){
    return welcomemsg;
}


public void setHostid(String hostid){
    this.hostid = hostid;
}


public Date getCreatetime(){
    return createtime;
}


public void setSubtype(String subtype){
    this.subtype = subtype;
}


public String getHostid(){
    return hostid;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getMediapath(){
    return mediapath;
}


public String getEnableai(){
    return enableai;
}


public String getAgentno(){
    return agentno;
}


public String getWaitmsg(){
    return waitmsg;
}


public void setExtention(String extention){
    this.extention = extention;
}


public void setWaitmsg(String waitmsg){
    this.waitmsg = waitmsg;
}


@Column(name = "srecord")
public boolean isRecord(){
    return record;
}


public String getQueid(){
    return queid;
}


public void setBustype(String bustype){
    this.bustype = bustype;
}


public void setEnablewebrtc(boolean enablewebrtc){
    this.enablewebrtc = enablewebrtc;
}


public String getPassword(){
    return password;
}


public String getOrgi(){
    return orgi;
}


}