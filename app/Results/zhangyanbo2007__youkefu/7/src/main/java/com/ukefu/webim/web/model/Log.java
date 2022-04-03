package com.ukefu.webim.web.model;
 import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_log")
@org.hibernate.annotations.Proxy(lazy = false)
public class Log implements Serializable{

 private  long serialVersionUID;

 private  String id;

 private  String orgi;

 private  String flowid;

 private  String logtype;

 private  Date createdate;

 private  String msg;

 private  String levels;

 private  String thread;

 private  String clazz;

 private  String files;

 private  String linenumber;

 private  String method;

 private  String startid;

 private  String errorinfo;

 private  String triggerwarning;

 private  String triggertime;

 private  int triggertimes;

 private  String logtime;

 private  String name;

 private  String code;

 private  String userid;

 private  String username;

 private  String memo;

 private  String ipaddr;

 private  String port;

public Log() {
}public Log(String orgi, String flowid, String msg, String levels, String thread) {
    this.id = String.valueOf(System.nanoTime());
    this.orgi = orgi;
    this.flowid = flowid;
    this.createdate = new Date();
    this.msg = msg;
    this.levels = levels;
    this.thread = thread;
}
public String getTriggertime(){
    return triggertime;
}


public String getName(){
    return name;
}


public String getMethod(){
    return method;
}


public String getLinenumber(){
    return linenumber;
}


public void setStartid(String startid){
    this.startid = startid;
}


public String getIpaddr(){
    return ipaddr;
}


public void setLogtype(String logtype){
    this.logtype = logtype;
}


public void setId(String id){
    this.id = id;
}


public String getErrorinfo(){
    return errorinfo;
}


public void setErrorinfo(String errorinfo){
    this.errorinfo = errorinfo;
}


public String getCode(){
    return code;
}


public void setThread(String thread){
    this.thread = thread;
}


public void setCreatedate(Date createdate){
    this.createdate = createdate;
}


public void setCode(String code){
    this.code = code;
}


public String getFiles(){
    return files;
}


public void setLevels(String levels){
    this.levels = levels;
}


public String getMemo(){
    return memo;
}


public void setMsg(String msg){
    this.msg = msg;
}


public void setName(String name){
    this.name = name;
}


public String getMsg(){
    return msg;
}


public Date getCreatedate(){
    return createdate;
}


public void setClazz(String clazz){
    this.clazz = clazz;
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


public void setFlowid(String flowid){
    this.flowid = flowid;
}


public void setIpaddr(String ipaddr){
    this.ipaddr = ipaddr;
}


public String getLevels(){
    return levels;
}


public void setPort(String port){
    this.port = port;
}


public void setFiles(String files){
    this.files = files;
}


public String getUsername(){
    return username;
}


public void setTriggertimes(int triggertimes){
    this.triggertimes = triggertimes;
}


public void setLinenumber(String linenumber){
    this.linenumber = linenumber;
}


public String getUserid(){
    return userid;
}


public void setUserid(String userid){
    this.userid = userid;
}


public String getClazz(){
    return clazz;
}


public String getLogtime(){
    return logtime;
}


public void setUsername(String username){
    this.username = username;
}


public void setLogtime(String logtime){
    this.logtime = logtime;
}


public void setTriggertime(String triggertime){
    this.triggertime = triggertime;
}


public void setMethod(String method){
    if (method != null && method.length() > 255) {
        method = method.substring(0, 255);
    }
    this.method = method;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getLogtype(){
    return logtype;
}


public String getTriggerwarning(){
    return triggerwarning;
}


public String getPort(){
    return port;
}


public String getFlowid(){
    return flowid;
}


public String getOrgi(){
    return orgi;
}


public String getThread(){
    return thread;
}


public int getTriggertimes(){
    return triggertimes;
}


public void setTriggerwarning(String triggerwarning){
    this.triggerwarning = triggerwarning;
}


public String getStartid(){
    return startid;
}


}