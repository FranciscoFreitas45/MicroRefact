package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
import com.ukefu.util.UKTools;
import com.ukefu.util.event.UserEvent;
@Entity
@Table(name = "uk_userevent")
@Proxy(lazy = false)
public class UserHistory implements UserEvent{

 private  long serialVersionUID;

 private  String id;

 private  String username;

 private  String creater;

 private  Date createtime;

 private  String orgi;

 private  String title;

 private  Date updatetime;

 private  String maintype;

 private  String subtype;

 private  String ostype;

 private  String browser;

 private  String appid;

 private  String mobile;

 private  String referer;

 private  String name;

 private  boolean admin;

 private  boolean accessnum;

 private  String ip;

 private  String hostname;

 private  String country;

 private  String region;

 private  String city;

 private  String isp;

 private  String province;

 private  String url;

 private  String sessionid;

 private  String param;

 private  int times;

 private  String createdate;

 private  String model;


public void setReferer(String referer){
    this.referer = referer;
}


public String getMaintype(){
    return maintype;
}


public void setCountry(String country){
    this.country = country;
}


public String getName(){
    return name;
}


public String getCountry(){
    return country;
}


public void setProvince(String province){
    this.province = province;
}


public void setHostname(String hostname){
    this.hostname = hostname;
}


public void setAppid(String appid){
    this.appid = appid;
}


public String getReferer(){
    return referer;
}


public String getTitle(){
    return title;
}


public void setSessionid(String sessionid){
    this.sessionid = sessionid;
}


public String getOstype(){
    return ostype;
}


public void setId(String id){
    this.id = id;
}


public String getCity(){
    return city;
}


public int getTimes(){
    return times;
}


public String getParam(){
    return param;
}


public String getModel(){
    return model;
}


public void setCreatedate(String createdate){
    this.createdate = createdate;
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


public boolean isAdmin(){
    return admin;
}


public void setIp(String ip){
    this.ip = ip;
}


public void setUrl(String url){
    this.url = url;
}


public String getUrl(){
    return url;
}


public void setMobile(String mobile){
    this.mobile = mobile;
}


public boolean isAccessnum(){
    return accessnum;
}


public String getRegion(){
    return region;
}


public String getSubtype(){
    return subtype;
}


public void setModel(String model){
    this.model = model;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setTimes(int times){
    this.times = times;
}


public void setAdmin(boolean admin){
    this.admin = admin;
}


public String getBrowser(){
    return browser;
}


public void setName(String name){
    this.name = name;
}


public String getCreatedate(){
    return createdate;
}


public String getIp(){
    return ip;
}


public void setIsp(String isp){
    this.isp = isp;
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
@GenericGenerator(name = "system-uuid", strategy = "assigned")
public String getId(){
    return id;
}


public String getUsername(){
    return username;
}


public void setAccessnum(boolean accessnum){
    this.accessnum = accessnum;
}


public String getHostname(){
    return hostname;
}


public Date getCreatetime(){
    return createtime;
}


public void setMaintype(String maintype){
    this.maintype = maintype;
}


public void setSubtype(String subtype){
    this.subtype = subtype;
}


public String getIsp(){
    return isp;
}


public String getProvince(){
    return province;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public void setBrowser(String browser){
    this.browser = browser;
}


public String getSessionid(){
    return sessionid;
}


public void setOstype(String ostype){
    this.ostype = ostype;
}


public void setRegion(String region){
    this.region = region;
}


public void setUsername(String username){
    this.username = username;
}


public String getAppid(){
    return appid;
}


public String getOrgi(){
    return orgi;
}


public String getMobile(){
    return mobile;
}


}