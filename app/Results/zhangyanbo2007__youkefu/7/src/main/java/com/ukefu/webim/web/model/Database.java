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
@Entity
@Table(name = "uk_databasetask")
@org.hibernate.annotations.Proxy(lazy = false)
public class Database implements Serializable{

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String code;

 private  String address;

 private  String lastcrawl;

 private  String account;

 private  String password;

 private  String attachment;

 private  int port;

 private  String databasetype;

 private  String databasename;

 private  String connectparam;

 private  String encoding;

 private  String databaseurl;

 private  String driverclazz;

 private  String configure;

 private  String secureconf;

 private  String previewtemplet;

 private  String listblocktemplet;

 private  String sqldialect;

 private  String orgi;

 private  String jndiname;

 private  String jndiparam;

 private  String connctiontype;

 private  String doctype;

 private  String secure;

 private  Date lastupdate;

 private  String taskname;

 private  String taskplan;

 private  String taskstatus;

 private  String tasktype;

 private  Date createtime;

 private  String userid;

 private  String groupid;

 private  String createuser;


public void setPassword(String password){
    this.password = password;
}


public String getName(){
    return name;
}


public String getListblocktemplet(){
    return listblocktemplet;
}


public String getTaskstatus(){
    return taskstatus;
}


public void setJndiname(String jndiname){
    this.jndiname = jndiname;
}


public String getDatabasetype(){
    return databasetype;
}


public String getEncoding(){
    return encoding;
}


public void setListblocktemplet(String listblocktemplet){
    this.listblocktemplet = listblocktemplet;
}


public void setConnctiontype(String connctiontype){
    this.connctiontype = connctiontype;
}


public void setId(String id){
    this.id = id;
}


public String getSqldialect(){
    return sqldialect;
}


public void setPreviewtemplet(String previewtemplet){
    this.previewtemplet = previewtemplet;
}


public String getCode(){
    return code;
}


public void setTaskplan(String taskplan){
    this.taskplan = taskplan;
}


public String getAttachment(){
    return attachment;
}


public void setGroupid(String groupid){
    this.groupid = groupid;
}


public void setSqldialect(String sqldialect){
    this.sqldialect = sqldialect;
}


public void setCode(String code){
    this.code = code;
}


public void setLastupdate(Date lastupdate){
    this.lastupdate = lastupdate;
}


public void setDatabaseurl(String databaseurl){
    this.databaseurl = databaseurl;
}


@Transient
public String getType(){
    return "database";
}


public String getConnectparam(){
    return connectparam;
}


public String getDriverclazz(){
    return driverclazz;
}


public String getLastcrawl(){
    return lastcrawl;
}


public void setSecure(String secure){
    this.secure = secure;
}


public void setTaskname(String taskname){
    this.taskname = taskname;
}


public void setName(String name){
    this.name = name;
}


public String getTasktype(){
    return tasktype;
}


public String getSecureconf(){
    return secureconf;
}


public void setEncoding(String encoding){
    this.encoding = encoding;
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


public String getConfigure(){
    return configure;
}


public void setDriverclazz(String driverclazz){
    this.driverclazz = driverclazz;
}


public void setPort(int port){
    this.port = port;
}


public String getConnctiontype(){
    return connctiontype;
}


public String getTaskplan(){
    return taskplan;
}


public String getPreviewtemplet(){
    return previewtemplet;
}


public String getCreateuser(){
    return createuser;
}


public Date getCreatetime(){
    return createtime;
}


public String getJndiparam(){
    return jndiparam;
}


public String getAccount(){
    return account;
}


public void setDatabasetype(String databasetype){
    this.databasetype = databasetype;
}


public void setCreateuser(String createuser){
    this.createuser = createuser;
}


public String getAddress(){
    return address;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public void setConnectparam(String connectparam){
    this.connectparam = connectparam;
}


public String getUserid(){
    return userid;
}


public String getDatabasename(){
    return databasename;
}


public String getDatabaseurl(){
    return databaseurl;
}


public void setUserid(String userid){
    this.userid = userid;
}


public void setAddress(String address){
    this.address = address;
}


public void setDatabasename(String databasename){
    this.databasename = databasename;
}


public void setConfigure(String configure){
    this.configure = configure;
}


public void setSecureconf(String secureconf){
    this.secureconf = secureconf;
}


public String getGroupid(){
    return groupid;
}


public void setTasktype(String tasktype){
    this.tasktype = tasktype;
}


public String getJndiname(){
    return jndiname;
}


public void setJndiparam(String jndiparam){
    this.jndiparam = jndiparam;
}


public void setDoctype(String doctype){
    this.doctype = doctype;
}


public Date getLastupdate(){
    return lastupdate;
}


public String getPassword(){
    return password;
}


public String getDoctype(){
    return doctype;
}


public void setTaskstatus(String taskstatus){
    this.taskstatus = taskstatus;
}


public int getPort(){
    return port;
}


public String getTaskname(){
    return taskname;
}


public void setLastcrawl(String lastcrawl){
    this.lastcrawl = lastcrawl;
}


public String getOrgi(){
    return orgi;
}


public void setAttachment(String attachment){
    this.attachment = attachment;
}


public void setAccount(String account){
    this.account = account;
}


public String getSecure(){
    return secure;
}


}