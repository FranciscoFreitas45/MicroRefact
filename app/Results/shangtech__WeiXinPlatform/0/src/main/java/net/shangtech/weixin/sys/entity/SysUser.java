package net.shangtech.weixin.sys.entity;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import net.shangtech.ssh.core.base.IBaseEntity;
@Entity
@Table(name = "sys_user")
public class SysUser extends IBaseEntity{

 private  long serialVersionUID;

@Column(name = "username")
 private  String username;

@Column(name = "password")
 private  String password;

@Column(name = "mobile")
 private  String mobile;

@Column(name = "email")
 private  String email;

@Column(name = "qq")
 private  String qq;

@Column(name = "create_time")
 private  Date createTime;

@Column(name = "wx_name")
 private  String wxName;

@Column(name = "wx_number")
 private  String wxNumber;

@Column(name = "token")
 private  String token;

@Column(name = "appid")
 private  String appid;

@Column(name = "appkey")
 private  String appkey;

@Column(name = "province")
 private  String province;

@Column(name = "city")
 private  String city;

@Column(name = "description")
 private  String description;

@Column(name = "openid")
 private  String openid;

@Column(name = "user_type")
 private  Integer userType;

@Column(name = "site_logo")
 private  String siteLogo;

@Column(name = "site_tel")
 private  String siteTel;


public void setPassword(String password){
    this.password = password;
}


public String getOpenid(){
    return openid;
}


public Date getCreateTime(){
    return createTime;
}


public void setProvince(String province){
    this.province = province;
}


public void setDescription(String description){
    this.description = description;
}


public String getQq(){
    return qq;
}


public void setAppid(String appid){
    this.appid = appid;
}


public String getDescription(){
    return description;
}


public String getUsername(){
    return username;
}


public void setAppkey(String appkey){
    this.appkey = appkey;
}


public void setWxName(String wxName){
    this.wxName = wxName;
}


public void setUserType(Integer userType){
    this.userType = userType;
}


public String getProvince(){
    return province;
}


public void setSiteLogo(String siteLogo){
    this.siteLogo = siteLogo;
}


public String getSiteLogo(){
    return siteLogo;
}


public String getCity(){
    return city;
}


public Integer getUserType(){
    return userType;
}


public void setUsername(String username){
    this.username = username;
}


public void setWxNumber(String wxNumber){
    this.wxNumber = wxNumber;
}


public String getWxName(){
    return wxName;
}


public void setCity(String city){
    this.city = city;
}


public void setQq(String qq){
    this.qq = qq;
}


public String getSiteTel(){
    return siteTel;
}


public String getAppkey(){
    return appkey;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public String getWxNumber(){
    return wxNumber;
}


public String getAppid(){
    return appid;
}


public String getPassword(){
    return password;
}


public void setMobile(String mobile){
    this.mobile = mobile;
}


public void setEmail(String email){
    this.email = email;
}


public void setToken(String token){
    this.token = token;
}


public String getToken(){
    return token;
}


public void setOpenid(String openid){
    this.openid = openid;
}


public String getMobile(){
    return mobile;
}


public String getEmail(){
    return email;
}


public void setSiteTel(String siteTel){
    this.siteTel = siteTel;
}


}