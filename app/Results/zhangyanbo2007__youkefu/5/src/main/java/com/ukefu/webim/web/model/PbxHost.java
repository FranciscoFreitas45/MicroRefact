package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_callcenter_pbxhost")
@org.hibernate.annotations.Proxy(lazy = false)
public class PbxHost {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String hostname;

 private  String ipaddr;

 private  int port;

 private  int sipport;

 private  String password;

 private  String blacklist;

 private  String whitelist;

 private  boolean connected;

 private  boolean callcenter;

 private  String recordpath;

 private  String asrrecordpath;

 private  String ttsrecordpath;

 private  String ivrpath;

 private  String fspath;

 private  String device;

 private  boolean afterprocess;

 private  String orgi;

 private  boolean autoanswer;

 private  boolean sipautoanswer;

 private  String abscodec;

 private  String callbacktype;

 private  String callbacknumber;

 private  String creater;

 private  String enableai;

 private  String aiid;

 private  String sceneid;

 private  String welcomemsg;

 private  String waitmsg;

 private  String tipmessage;

 private  boolean enablewebrtc;

 private  String webrtcaddress;

 private  String webrtcport;

 private  boolean webrtcssl;

 private  String webrtcring;

 private  boolean dissipphone;

 private  int maxnumlength;

 private  int minnumlength;

 private  String ipregionblack;

 private  String ipregionwhite;

 private  boolean savekillevent;

 private  Date createtime;

 private  Date updatetime;


public void setPassword(String password){
    this.password = password;
}


public void setAiid(String aiid){
    this.aiid = aiid;
}


public String getName(){
    return name;
}


public void setHostname(String hostname){
    this.hostname = hostname;
}


public String getBlacklist(){
    return blacklist;
}


public int getMinnumlength(){
    return minnumlength;
}


public void setAutoanswer(boolean autoanswer){
    this.autoanswer = autoanswer;
}


public String getTipmessage(){
    return tipmessage;
}


public String getWebrtcring(){
    return webrtcring;
}


public void setAbscodec(String abscodec){
    this.abscodec = abscodec;
}


public String getIpaddr(){
    return ipaddr;
}


public void setFspath(String fspath){
    this.fspath = fspath;
}


public void setId(String id){
    this.id = id;
}


public void setEnableai(String enableai){
    this.enableai = enableai;
}


public String getSceneid(){
    return sceneid;
}


public void setTipmessage(String tipmessage){
    this.tipmessage = tipmessage;
}


public void setWebrtcaddress(String webrtcaddress){
    this.webrtcaddress = webrtcaddress;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getTtsrecordpath(){
    return ttsrecordpath;
}


public void setWhitelist(String whitelist){
    this.whitelist = whitelist;
}


public String getIpregionwhite(){
    return ipregionwhite;
}


public void setSipport(int sipport){
    this.sipport = sipport;
}


public String getWebrtcport(){
    return webrtcport;
}


public void setSavekillevent(boolean savekillevent){
    this.savekillevent = savekillevent;
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


public void setCallcenter(boolean callcenter){
    this.callcenter = callcenter;
}


public void setWebrtcssl(boolean webrtcssl){
    this.webrtcssl = webrtcssl;
}


public boolean isSipautoanswer(){
    return sipautoanswer;
}


public void setName(String name){
    this.name = name;
}


public void setIpregionblack(String ipregionblack){
    this.ipregionblack = ipregionblack;
}


public String getAiid(){
    return aiid;
}


public String getAsrrecordpath(){
    return asrrecordpath;
}


public void setIpregionwhite(String ipregionwhite){
    this.ipregionwhite = ipregionwhite;
}


public String getIvrpath(){
    return ivrpath;
}


public String getFspath(){
    return fspath;
}


public void setRecordpath(String recordpath){
    this.recordpath = recordpath;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


@Transient
public boolean isConnected(){
    return connected;
}


public void setWelcomemsg(String welcomemsg){
    this.welcomemsg = welcomemsg;
}


public String getWhitelist(){
    return whitelist;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


public void setCallbacknumber(String callbacknumber){
    this.callbacknumber = callbacknumber;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public void setIpaddr(String ipaddr){
    this.ipaddr = ipaddr;
}


public void setIvrpath(String ivrpath){
    this.ivrpath = ivrpath;
}


public void setWebrtcport(String webrtcport){
    this.webrtcport = webrtcport;
}


public void setPort(int port){
    this.port = port;
}


public void setTtsrecordpath(String ttsrecordpath){
    this.ttsrecordpath = ttsrecordpath;
}


public String getDevice(){
    return device;
}


public String getWelcomemsg(){
    return welcomemsg;
}


public void setMaxnumlength(int maxnumlength){
    this.maxnumlength = maxnumlength;
}


public void setAfterprocess(boolean afterprocess){
    this.afterprocess = afterprocess;
}


public String getHostname(){
    return hostname;
}


public Date getCreatetime(){
    return createtime;
}


public String getCallbacktype(){
    return callbacktype;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public boolean isAutoanswer(){
    return autoanswer;
}


public String getWebrtcaddress(){
    return webrtcaddress;
}


public String getEnableai(){
    return enableai;
}


public void setDissipphone(boolean dissipphone){
    this.dissipphone = dissipphone;
}


public String getWaitmsg(){
    return waitmsg;
}


public int getSipport(){
    return sipport;
}


public void setWaitmsg(String waitmsg){
    this.waitmsg = waitmsg;
}


public boolean isSavekillevent(){
    return savekillevent;
}


public void setWebrtcring(String webrtcring){
    this.webrtcring = webrtcring;
}


public String getIpregionblack(){
    return ipregionblack;
}


public String getCallbacknumber(){
    return callbacknumber;
}


public String getAbscodec(){
    return abscodec;
}


public void setCallbacktype(String callbacktype){
    this.callbacktype = callbacktype;
}


public void setEnablewebrtc(boolean enablewebrtc){
    this.enablewebrtc = enablewebrtc;
}


public void setAsrrecordpath(String asrrecordpath){
    this.asrrecordpath = asrrecordpath;
}


public void setBlacklist(String blacklist){
    this.blacklist = blacklist;
}


public boolean isCallcenter(){
    return callcenter;
}


public void setDevice(String device){
    this.device = device;
}


public boolean isDissipphone(){
    return dissipphone;
}


public int getMaxnumlength(){
    return maxnumlength;
}


public String getPassword(){
    return password;
}


public int getPort(){
    return port;
}


public boolean isWebrtcssl(){
    return webrtcssl;
}


public String getRecordpath(){
    return recordpath;
}


public String getOrgi(){
    return orgi;
}


public void setSipautoanswer(boolean sipautoanswer){
    this.sipautoanswer = sipautoanswer;
}


public boolean isAfterprocess(){
    return afterprocess;
}


public void setConnected(boolean connected){
    this.connected = connected;
}


public void setMinnumlength(int minnumlength){
    this.minnumlength = minnumlength;
}


}