package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_callcenter_ivr")
@org.hibernate.annotations.Proxy(lazy = false)
public class IvrMenu {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String orgi;

 private  String creater;

 private  String type;

 private  Date createtime;

 private  Date updatetime;

 private  String hostid;

 private  String extentionid;

 private  String greetlong;

 private  String greetshort;

 private  String invalidsound;

 private  String exitsound;

 private  String confirmmacro;

 private  String confirmkey;

 private  String ttsengine;

 private  String ttsvoice;

 private  String confirmattempts;

 private  int timeout;

 private  int interdigittimeout;

 private  int maxfailures;

 private  int maxtimeouts;

 private  int digitlen;

 private  String menucontent;

 private  String action;

 private  String digits;

 private  String param;

 private  String parentid;


public void setDigits(String digits){
    this.digits = digits;
}


public String getName(){
    return name;
}


public void setMaxfailures(int maxfailures){
    this.maxfailures = maxfailures;
}


public void setConfirmattempts(String confirmattempts){
    this.confirmattempts = confirmattempts;
}


public void setGreetlong(String greetlong){
    this.greetlong = greetlong;
}


public String getExitsound(){
    return exitsound;
}


public void setId(String id){
    this.id = id;
}


public String getInvalidsound(){
    return invalidsound;
}


public void setGreetshort(String greetshort){
    this.greetshort = greetshort;
}


public String getParam(){
    return param;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getConfirmattempts(){
    return confirmattempts;
}


public String getTtsvoice(){
    return ttsvoice;
}


public String getExtentionid(){
    return extentionid;
}


public String getConfirmmacro(){
    return confirmmacro;
}


public String getType(){
    return type;
}


public int getMaxtimeouts(){
    return maxtimeouts;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setInvalidsound(String invalidsound){
    this.invalidsound = invalidsound;
}


public void setConfirmkey(String confirmkey){
    this.confirmkey = confirmkey;
}


public String getMenucontent(){
    return menucontent;
}


public int getMaxfailures(){
    return maxfailures;
}


public void setName(String name){
    this.name = name;
}


public int getDigitlen(){
    return digitlen;
}


public String getTtsengine(){
    return ttsengine;
}


public void setAction(String action){
    this.action = action;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public void setParam(String param){
    this.param = param;
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


public String getGreetlong(){
    return greetlong;
}


public void setHostid(String hostid){
    this.hostid = hostid;
}


public Date getCreatetime(){
    return createtime;
}


public int getTimeout(){
    return timeout;
}


public String getHostid(){
    return hostid;
}


public void setExitsound(String exitsound){
    this.exitsound = exitsound;
}


public void setParentid(String parentid){
    this.parentid = parentid;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getParentid(){
    return parentid;
}


public void setInterdigittimeout(int interdigittimeout){
    this.interdigittimeout = interdigittimeout;
}


public void setExtentionid(String extentionid){
    this.extentionid = extentionid;
}


public int getInterdigittimeout(){
    return interdigittimeout;
}


public String getGreetshort(){
    return greetshort;
}


public String getConfirmkey(){
    return confirmkey;
}


public void setTtsengine(String ttsengine){
    this.ttsengine = ttsengine;
}


public void setMenucontent(String menucontent){
    this.menucontent = menucontent;
}


public void setMaxtimeouts(int maxtimeouts){
    this.maxtimeouts = maxtimeouts;
}


public String getAction(){
    return action;
}


public void setType(String type){
    this.type = type;
}


public String getDigits(){
    return digits;
}


public void setTimeout(int timeout){
    this.timeout = timeout;
}


public void setConfirmmacro(String confirmmacro){
    this.confirmmacro = confirmmacro;
}


public String getOrgi(){
    return orgi;
}


public void setTtsvoice(String ttsvoice){
    this.ttsvoice = ttsvoice;
}


public void setDigitlen(int digitlen){
    this.digitlen = digitlen;
}


}