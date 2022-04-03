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
@Table(name = "uk_snsaccount")
@org.hibernate.annotations.Proxy(lazy = false)
public class SNSAccount {

 private  String id;

 private  String snsid;

 private  String name;

 private  String code;

 private  String username;

 private  String password;

 private  String snstype;

 private  Date createtime;

 private  Date updatetime;

 private  int expirestime;

 private  String account;

 private  String verify;

 private  String headimg;

 private  String qrcode;

 private  String alias;

 private  String openpay;

 private  String openshake;

 private  String oepnscan;

 private  String opencard;

 private  String openstore;

 private  String refreshtoken;

 private  String authaccesstoken;

 private  String allowremote;

 private  String email;

 private  String userno;

 private  String token;

 private  String apipoint;

 private  String appkey;

 private  String secret;

 private  String aeskey;

 private  String baseURL;

 private  String apptoken;

 private  String sessionkey;

 private  boolean defaultaccount;

 private  String moreparam;

 private  String orgi;

 private  String lastatupdate;

 private  String lastprimsgupdate;

 private  String status;

 private  boolean agent;


public String getUserno(){
    return userno;
}


public void setPassword(String password){
    this.password = password;
}


public String getName(){
    return name;
}


public void setQrcode(String qrcode){
    this.qrcode = qrcode;
}


public String getSnstype(){
    return snstype;
}


public void setHeadimg(String headimg){
    this.headimg = headimg;
}


@Transient
public String getStatus(){
    return status;
}


public void setAppkey(String appkey){
    this.appkey = appkey;
}


public void setApipoint(String apipoint){
    this.apipoint = apipoint;
}


public String getOpenstore(){
    return openstore;
}


public void setOpenstore(String openstore){
    this.openstore = openstore;
}


public boolean isAgent(){
    return agent;
}


public void setVerify(String verify){
    this.verify = verify;
}


public void setId(String id){
    this.id = id;
}


public String getHeadimg(){
    return headimg;
}


public void setSnstype(String snstype){
    this.snstype = snstype;
}


public String getOpencard(){
    return opencard;
}


public String getCode(){
    return code;
}


public void setRefreshtoken(String refreshtoken){
    this.refreshtoken = refreshtoken;
}


public String getRefreshtoken(){
    return refreshtoken;
}


public void setAeskey(String aeskey){
    this.aeskey = aeskey;
}


public void setExpirestime(int expirestime){
    this.expirestime = expirestime;
}


public void setCode(String code){
    this.code = code;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setMoreparam(String moreparam){
    this.moreparam = moreparam;
}


public boolean isDefaultaccount(){
    return defaultaccount;
}


public void setApptoken(String apptoken){
    this.apptoken = apptoken;
}


public void setLastatupdate(String lastatupdate){
    this.lastatupdate = lastatupdate;
}


public void setOpenpay(String openpay){
    this.openpay = openpay;
}


public String getSessionkey(){
    return sessionkey;
}


public void setEmail(String email){
    this.email = email;
}


public String getSecret(){
    return secret;
}


public String getEmail(){
    return email;
}


public void setUserno(String userno){
    this.userno = userno;
}


public void setOpenshake(String openshake){
    this.openshake = openshake;
}


public void setAlias(String alias){
    this.alias = alias;
}


public void setAllowremote(String allowremote){
    this.allowremote = allowremote;
}


public String getAlias(){
    return alias;
}


public void setOepnscan(String oepnscan){
    this.oepnscan = oepnscan;
}


public String getAuthaccesstoken(){
    return authaccesstoken;
}


public void setName(String name){
    this.name = name;
}


public String getAeskey(){
    return aeskey;
}


public void setLastprimsgupdate(String lastprimsgupdate){
    this.lastprimsgupdate = lastprimsgupdate;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public String getMoreparam(){
    return moreparam;
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


public String getUsername(){
    return username;
}


public String getBaseURL(){
    return baseURL;
}


public String getAllowremote(){
    return allowremote;
}


public void setDefaultaccount(boolean defaultaccount){
    this.defaultaccount = defaultaccount;
}


public Date getCreatetime(){
    return createtime;
}


public String getAccount(){
    return account;
}


public String getVerify(){
    return verify;
}


public void setOpencard(String opencard){
    this.opencard = opencard;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getQrcode(){
    return qrcode;
}


public String getApipoint(){
    return apipoint;
}


public void setUsername(String username){
    this.username = username;
}


public void setSnsid(String snsid){
    this.snsid = snsid;
}


public String getAppkey(){
    return appkey;
}


public String getOpenpay(){
    return openpay;
}


public String getOpenshake(){
    return openshake;
}


public String getLastprimsgupdate(){
    return lastprimsgupdate;
}


public void setStatus(String status){
    this.status = status;
}


public void setAgent(boolean agent){
    this.agent = agent;
}


public String getPassword(){
    return password;
}


public String getOepnscan(){
    return oepnscan;
}


public void setAuthaccesstoken(String authaccesstoken){
    this.authaccesstoken = authaccesstoken;
}


public void setToken(String token){
    this.token = token;
}


public String getApptoken(){
    return apptoken;
}


public void setSecret(String secret){
    this.secret = secret;
}


public String getToken(){
    return token;
}


public void setBaseURL(String baseURL){
    this.baseURL = baseURL;
}


public void setSessionkey(String sessionkey){
    this.sessionkey = sessionkey;
}


public String getOrgi(){
    return orgi;
}


public String getSnsid(){
    return snsid;
}


public void setAccount(String account){
    this.account = account;
}


public String getLastatupdate(){
    return lastatupdate;
}


public String toString(){
    return this.id;
}


public int getExpirestime(){
    return expirestime;
}


}