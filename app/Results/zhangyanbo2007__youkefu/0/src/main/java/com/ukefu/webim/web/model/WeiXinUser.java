package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_weixinuser")
@org.hibernate.annotations.Proxy(lazy = false)
public class WeiXinUser {

 private  long serialVersionUID;

 private  String id;

 private  String snsid;

 private  boolean subscribe;

 private  String openid;

 private  String nickname;

 private  String sex;

 private  String language;

 private  String city;

 private  String province;

 private  String country;

 private  String headimgurl;

 private  String subscribetime;

 private  String unionid;

 private  int sexid;

 private  String remark;

 private  int groupid;

 private  String orgi;

 private  String contactsid;


public void setUnionid(String unionid){
    this.unionid = unionid;
}


public String getOpenid(){
    return openid;
}


public void setCountry(String country){
    this.country = country;
}


public String getSubscribetime(){
    return subscribetime;
}


public String getUnionid(){
    return unionid;
}


public void setProvince(String province){
    this.province = province;
}


public String getCountry(){
    return country;
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


public void setContactsid(String contactsid){
    this.contactsid = contactsid;
}


public void setSexid(int sexid){
    this.sexid = sexid;
}


public void setRemark(String remark){
    this.remark = remark;
}


public String getHeadimgurl(){
    return headimgurl;
}


public String getRemark(){
    return remark;
}


public String getProvince(){
    return province;
}


public void setId(String id){
    this.id = id;
}


@Transient
public Date getSubTime(){
    Date date = new Date();
    if (!StringUtils.isBlank(this.subscribetime)) {
        long time = Long.parseLong(this.subscribetime);
        date = new Date(time * 1000);
    }
    return date;
}


public String getCity(){
    return city;
}


public String getLanguage(){
    return language;
}


public void setGroupid(int groupid){
    this.groupid = groupid;
}


public void setSnsid(String snsid){
    this.snsid = snsid;
}


public void setCity(String city){
    this.city = city;
}


public void setSex(String sex){
    this.sex = sex;
}


public int getSexid(){
    return sexid;
}


public boolean isSubscribe(){
    return subscribe;
}


public void setSubscribetime(String subscribetime){
    this.subscribetime = subscribetime;
}


public int getGroupid(){
    return groupid;
}


public String getContactsid(){
    return contactsid;
}


public String getNickname(){
    return nickname;
}


public void setOpenid(String openid){
    this.openid = openid;
}


public void setHeadimgurl(String headimgurl){
    this.headimgurl = headimgurl;
}


public String getSnsid(){
    return snsid;
}


public String getSex(){
    return sex;
}


public String getOrgi(){
    return orgi;
}


public void setSubscribe(boolean subscribe){
    this.subscribe = subscribe;
}


public void setNickname(String nickname){
    this.nickname = nickname;
}


public void setLanguage(String language){
    this.language = language;
}


}