package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
import com.ukefu.util.event.UserEvent;
@Entity
@Table(name = "uk_onlineuser")
@Proxy(lazy = false)
public class OnlineUser implements UserEvent{

 private  long serialVersionUID;

 private  String creater;

 private  String datastatus;

 private  String id;

 private  String impid;

 private  String appid;

 private  String ipcode;

 private  String orgi;

 private  String channel;

 private  String owner;

 private  String processid;

 private  String shares;

 private  Date updatetime;

 private  String updateuser;

 private  String username;

 private  String wfstatus;

 private  String resolution;

 private  String opersystem;

 private  String browser;

 private  String status;

 private  String userid;

 private  Date logintime;

 private  String sessionid;

 private  Date createtime;

 private  String usertype;

 private  String optype;

 private  String mobile;

 private  String olduser;

 private  String ip;

 private  String hostname;

 private  String country;

 private  String region;

 private  String city;

 private  String isp;

 private  String province;

 private  int betweentime;

 private  String datestr;

 private  String keyword;

 private  String source;

 private  String title;

 private  String url;

 private  String useragent;

 private  String contactsid;

 private  int invitetimes;

 private  String invitestatus;

 private  int refusetimes;

 private  Contacts contacts;


public Date getLogintime(){
    return this.logintime;
}


public void setCountry(String country){
    this.country = country;
}


public void setImpid(String impid){
    this.impid = impid;
}


public String getCountry(){
    return this.country;
}


public void setProvince(String province){
    this.province = province;
}


public void setHostname(String hostname){
    this.hostname = hostname;
}


public void setShares(String shares){
    this.shares = shares;
}


public void setProcessid(String processid){
    this.processid = processid;
}


public void setOptype(String optype){
    this.optype = optype;
}


public void setDatastatus(String datastatus){
    this.datastatus = datastatus;
}


public void setChannel(String channel){
    this.channel = channel;
}


public void setContactsid(String contactsid){
    this.contactsid = contactsid;
}


public String getOwner(){
    return this.owner;
}


public String getStatus(){
    return this.status;
}


public void setAppid(String appid){
    this.appid = appid;
}


public void setOwner(String owner){
    this.owner = owner;
}


public String getTitle(){
    return this.title;
}


public void setSessionid(String sessionid){
    this.sessionid = sessionid;
}


public void setKeyword(String keyword){
    this.keyword = keyword;
}


public String getChannel(){
    return channel;
}


public String getIpcode(){
    return this.ipcode;
}


public String getOlduser(){
    return this.olduser;
}


public void setId(String id){
    this.id = id;
}


public String getInvitestatus(){
    return invitestatus;
}


public String getCity(){
    return this.city;
}


public void setLogintime(Date logintime){
    this.logintime = logintime;
}


public String getUseragent(){
    return this.useragent;
}


public void setOlduser(String olduser){
    this.olduser = olduser;
}


public void setCity(String city){
    this.city = city;
}


public void setInvitetimes(int invitetimes){
    this.invitetimes = invitetimes;
}


public String getUpdateuser(){
    return this.updateuser;
}


public Date getUpdatetime(){
    return this.updatetime;
}


public void setResolution(String resolution){
    this.resolution = resolution;
}


public String getImpid(){
    return this.impid;
}


public void setTitle(String title){
    this.title = title;
}


public void setIp(String ip){
    this.ip = ip;
}


public void setUrl(String url){
    this.url = url;
}


public String getUrl(){
    return this.url;
}


public void setMobile(String mobile){
    this.mobile = mobile;
}


public String getContactsid(){
    return contactsid;
}


public String getRegion(){
    return this.region;
}


public String getSource(){
    return this.source;
}


public String getProcessid(){
    return this.processid;
}


public void setUsertype(String usertype){
    this.usertype = usertype;
}


public void setCreater(String creater){
    this.creater = creater;
}


public String getBrowser(){
    return this.browser;
}


public String getResolution(){
    return this.resolution;
}


public void setSource(String source){
    this.source = source;
}


public void setIpcode(String ipcode){
    this.ipcode = ipcode;
}


public void setWfstatus(String wfstatus){
    this.wfstatus = wfstatus;
}


public String getIp(){
    return this.ip;
}


public void setIsp(String isp){
    this.isp = isp;
}


public void setUpdateuser(String updateuser){
    this.updateuser = updateuser;
}


public void setDatestr(String datestr){
    this.datestr = datestr;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


public void setInvitestatus(String invitestatus){
    this.invitestatus = invitestatus;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "assigned")
public String getId(){
    return this.id;
}


public void setUseragent(String useragent){
    this.useragent = useragent;
}


@Transient
public Contacts getContacts(){
    return contacts;
}


public String getUsername(){
    return this.username;
}


public String getShares(){
    return this.shares;
}


public String getHostname(){
    return this.hostname;
}


public int getRefusetimes(){
    return refusetimes;
}


public Date getCreatetime(){
    return this.createtime;
}


public String getIsp(){
    return this.isp;
}


public String getProvince(){
    return this.province;
}


public void setOpersystem(String opersystem){
    this.opersystem = opersystem;
}


public String getDatestr(){
    return this.datestr;
}


public String getCreater(){
    return this.creater;
}


public void setBrowser(String browser){
    this.browser = browser;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getSessionid(){
    return this.sessionid;
}


public String getUserid(){
    return this.userid;
}


public void setUserid(String userid){
    this.userid = userid;
}


public void setRegion(String region){
    this.region = region;
}


public int getBetweentime(){
    return this.betweentime;
}


public String getDatastatus(){
    return this.datastatus;
}


public void setUsername(String username){
    this.username = username;
}


public void setRefusetimes(int refusetimes){
    this.refusetimes = refusetimes;
}


public void setContacts(Contacts contacts){
    this.contacts = contacts;
}


public void setStatus(String status){
    this.status = status;
}


public String getAppid(){
    return appid;
}


public String getOptype(){
    return this.optype;
}


public void setBetweentime(int betweentime){
    this.betweentime = betweentime;
}


public String getKeyword(){
    return this.keyword;
}


public String getOpersystem(){
    return this.opersystem;
}


public String getOrgi(){
    return this.orgi;
}


public String getWfstatus(){
    return this.wfstatus;
}


public String getMobile(){
    return this.mobile;
}


public String getUsertype(){
    return this.usertype;
}


public int getInvitetimes(){
    return invitetimes;
}


}