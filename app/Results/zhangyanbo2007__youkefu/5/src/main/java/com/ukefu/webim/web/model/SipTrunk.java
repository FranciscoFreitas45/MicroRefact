package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_callcenter_siptrunk")
@org.hibernate.annotations.Proxy(lazy = false)
public class SipTrunk {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String orgi;

 private  String creater;

 private  String type;

 private  Date createtime;

 private  Date updatetime;

 private  String hostid;

 private  String sipserver;

 private  int port;

 private  String extention;

 private  String outnumber;

 private  String prefix;

 private  String prefixstr;

 private  String dtmf;

 private  boolean register;

 private  boolean defaultsip;

 private  String title;

 private  String username;

 private  String authuser;

 private  String password;

 private  String fromuser;

 private  boolean transprotocol;

 private  String protocol;

 private  int exptime;

 private  int retry;

 private  int heartbeat;

 private  String sipcontent;

 private  String busyext;

 private  String notready;

 private  String noname;

 private  boolean enablecallagent;

 private  String province;

 private  String city;


public void setHeartbeat(int heartbeat){
    this.heartbeat = heartbeat;
}


public void setPassword(String password){
    this.password = password;
}


public String getName(){
    return name;
}


public String getExtention(){
    return extention;
}


public void setProvince(String province){
    this.province = province;
}


public void setFromuser(String fromuser){
    this.fromuser = fromuser;
}


public void setProtocol(String protocol){
    this.protocol = protocol;
}


public boolean isTransprotocol(){
    return transprotocol;
}


public void setExptime(int exptime){
    this.exptime = exptime;
}


public String getTitle(){
    return title;
}


public void setId(String id){
    this.id = id;
}


public boolean isDefaultsip(){
    return defaultsip;
}


public String getCity(){
    return city;
}


public void setNoname(String noname){
    this.noname = noname;
}


public int getHeartbeat(){
    return heartbeat;
}


public String getPrefix(){
    return prefix;
}


public void setDefaultsip(boolean defaultsip){
    this.defaultsip = defaultsip;
}


public String getSipserver(){
    return sipserver;
}


public void setCity(String city){
    this.city = city;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setTitle(String title){
    this.title = title;
}


public String getAuthuser(){
    return authuser;
}


public String getPrefixstr(){
    return prefixstr;
}


public String getType(){
    return type;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setNotready(String notready){
    this.notready = notready;
}


public void setName(String name){
    this.name = name;
}


public boolean isEnablecallagent(){
    return enablecallagent;
}


public void setPrefixstr(String prefixstr){
    this.prefixstr = prefixstr;
}


public void setRetry(int retry){
    this.retry = retry;
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
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public void setRegister(boolean register){
    this.register = register;
}


public void setSipserver(String sipserver){
    this.sipserver = sipserver;
}


public void setPort(int port){
    this.port = port;
}


public void setSipcontent(String sipcontent){
    this.sipcontent = sipcontent;
}


public String getOutnumber(){
    return outnumber;
}


public String getUsername(){
    return username;
}


public void setTransprotocol(boolean transprotocol){
    this.transprotocol = transprotocol;
}


public String getFromuser(){
    return fromuser;
}


public void setHostid(String hostid){
    this.hostid = hostid;
}


public void setBusyext(String busyext){
    this.busyext = busyext;
}


public Date getCreatetime(){
    return createtime;
}


public void setOutnumber(String outnumber){
    this.outnumber = outnumber;
}


public String getHostid(){
    return hostid;
}


public String getProvince(){
    return province;
}


public String getNoname(){
    return noname;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getSipcontent(){
    return sipcontent;
}


public int getRetry(){
    return retry;
}


public String getDtmf(){
    return dtmf;
}


public void setExtention(String extention){
    this.extention = extention;
}


public void setDtmf(String dtmf){
    this.dtmf = dtmf;
}


public void setUsername(String username){
    this.username = username;
}


public String getProtocol(){
    return protocol;
}


public void setType(String type){
    this.type = type;
}


public void setEnablecallagent(boolean enablecallagent){
    this.enablecallagent = enablecallagent;
}


public String getNotready(){
    return notready;
}


public String getPassword(){
    return password;
}


public int getPort(){
    return port;
}


public void setPrefix(String prefix){
    this.prefix = prefix;
}


public void setAuthuser(String authuser){
    this.authuser = authuser;
}


public String getBusyext(){
    return busyext;
}


public String getOrgi(){
    return orgi;
}


public int getExptime(){
    return exptime;
}


public boolean isRegister(){
    return register;
}


}