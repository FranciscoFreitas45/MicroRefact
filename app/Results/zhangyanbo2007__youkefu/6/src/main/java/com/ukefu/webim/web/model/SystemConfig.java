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
@Table(name = "uk_systemconfig")
@org.hibernate.annotations.Proxy(lazy = false)
public class SystemConfig {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String title;

 private  String theme;

 private  String loginlogo;

 private  String loginlogowidth;

 private  String loginlogoheight;

 private  String consolelogo;

 private  String consolelogowidth;

 private  String consolelogoheight;

 private  String favlogo;

 private  boolean savelog;

 private  String code;

 private  String orgi;

 private  String description;

 private  String memo;

 private  String creater;

 private  Date createtime;

 private  Date updatetime;

 private  String loglevel;

 private  boolean enablessl;

 private  String jksfile;

 private  String jkspassword;

 private  String mapkey;

 private  boolean workorders;

 private  String iconstr;

 private  String whitelistip;

 private  boolean callout;

 private  boolean auth;

 private  boolean callcenter;

 private  String cc_extention;

 private  String cc_quene;

 private  String cc_router;

 private  String cc_ivr;

 private  String cc_acl;

 private  String cc_siptrunk;

 private  String cc_callcenter;

 private  boolean enablemail;

 private  String emailid;

 private  String emailworkordertp;

 private  String mailcreatetp;

 private  String mailupdatetp;

 private  String mailprocesstp;

 private  boolean emailtocreater;

 private  String emailtocreatertp;

 private  boolean emailshowrecipient;

 private  boolean enablesms;

 private  String smsid;

 private  String smsworkordertp;

 private  String smscreatetp;

 private  String smsupdatetp;

 private  String smsprocesstp;

 private  boolean smstocreater;

 private  String smstocreatertp;

 private  boolean enabletneant;

 private  boolean tenantshare;

 private  String namealias;

 private  boolean tenantconsole;

 private  boolean enableregorgi;

 private  boolean enablevoice;

 private  boolean enabledis;


public void setLoginlogo(String loginlogo){
    this.loginlogo = loginlogo;
}


public void setEnabletneant(boolean enabletneant){
    this.enabletneant = enabletneant;
}


public void setEnableregorgi(boolean enableregorgi){
    this.enableregorgi = enableregorgi;
}


public void setNamealias(String namealias){
    this.namealias = namealias;
}


public void setLoglevel(String loglevel){
    this.loglevel = loglevel;
}


public boolean isEnablessl(){
    return enablessl;
}


public boolean isEnablevoice(){
    return enablevoice;
}


public void setSmstocreatertp(String smstocreatertp){
    this.smstocreatertp = smstocreatertp;
}


public void setConsolelogoheight(String consolelogoheight){
    this.consolelogoheight = consolelogoheight;
}


public boolean isSavelog(){
    return savelog;
}


public String getCc_router(){
    return cc_router;
}


public String getMailcreatetp(){
    return mailcreatetp;
}


public String getCode(){
    return code;
}


public void setCc_extention(String cc_extention){
    this.cc_extention = cc_extention;
}


public String getCc_extention(){
    return cc_extention;
}


public void setCc_quene(String cc_quene){
    this.cc_quene = cc_quene;
}


public String getSmstocreatertp(){
    return smstocreatertp;
}


public String getLoginlogo(){
    return loginlogo;
}


public String getTheme(){
    return theme;
}


public String getMailupdatetp(){
    return mailupdatetp;
}


public String getConsolelogoheight(){
    return consolelogoheight;
}


@Transient
public String getStyleColor(){
    String color = "#32c24d  !important;";
    if (!StringUtils.isBlank(this.theme) && this.theme.equals("01")) {
        color = "#32c24d  !important;";
    } else if (!StringUtils.isBlank(this.theme) && this.theme.equals("02")) {
        color = "#32c24d  !important;";
    } else if (!StringUtils.isBlank(this.theme) && this.theme.equals("03")) {
        color = "#1E90FF  !important;";
    }
    return color;
}


public String getEmailworkordertp(){
    return emailworkordertp;
}


public void setCode(String code){
    this.code = code;
}


public boolean isEnableregorgi(){
    return enableregorgi;
}


public void setEmailworkordertp(String emailworkordertp){
    this.emailworkordertp = emailworkordertp;
}


public String getMemo(){
    return memo;
}


public void setConsolelogo(String consolelogo){
    this.consolelogo = consolelogo;
}


public String getJkspassword(){
    return jkspassword;
}


public void setTheme(String theme){
    this.theme = theme;
}


public void setSavelog(boolean savelog){
    this.savelog = savelog;
}


public void setSmstocreater(boolean smstocreater){
    this.smstocreater = smstocreater;
}


public void setName(String name){
    this.name = name;
}


public void setSmsid(String smsid){
    this.smsid = smsid;
}


public String getSmsupdatetp(){
    return smsupdatetp;
}


public boolean isTenantshare(){
    return tenantshare;
}


public void setTenantshare(boolean tenantshare){
    this.tenantshare = tenantshare;
}


public void setDescription(String description){
    this.description = description;
}


public String getWhitelistip(){
    return whitelistip;
}


public void setSmsupdatetp(String smsupdatetp){
    this.smsupdatetp = smsupdatetp;
}


public boolean isAuth(){
    return auth;
}


public void setSmsprocesstp(String smsprocesstp){
    this.smsprocesstp = smsprocesstp;
}


public void setCc_ivr(String cc_ivr){
    this.cc_ivr = cc_ivr;
}


public Date getCreatetime(){
    return createtime;
}


public void setConsolelogowidth(String consolelogowidth){
    this.consolelogowidth = consolelogowidth;
}


public String getSmsid(){
    return smsid;
}


public void setMapkey(String mapkey){
    this.mapkey = mapkey;
}


public void setCc_router(String cc_router){
    this.cc_router = cc_router;
}


public void setAuth(boolean auth){
    this.auth = auth;
}


public void setMailupdatetp(String mailupdatetp){
    this.mailupdatetp = mailupdatetp;
}


public void setEmailid(String emailid){
    this.emailid = emailid;
}


public boolean isEnabletneant(){
    return enabletneant;
}


public boolean isCallcenter(){
    return callcenter;
}


public void setSmscreatetp(String smscreatetp){
    this.smscreatetp = smscreatetp;
}


public void setWhitelistip(String whitelistip){
    this.whitelistip = whitelistip;
}


public String getCc_callcenter(){
    return cc_callcenter;
}


public boolean isEnablesms(){
    return enablesms;
}


public void setEnablessl(boolean enablessl){
    this.enablessl = enablessl;
}


@Transient
public String getBgColor(){
    String color = "background-color:#32c24d;";
    if (!StringUtils.isBlank(this.theme) && this.theme.equals("01")) {
        color = "background-color:#32c24d !important;";
    } else if (!StringUtils.isBlank(this.theme) && this.theme.equals("02")) {
        color = "background-color:#32c24d !important;";
    } else if (!StringUtils.isBlank(this.theme) && this.theme.equals("03")) {
        color = "background-color:#1E90FF !important;";
    }
    return color;
}


public boolean isCallout(){
    return callout;
}


public String getName(){
    return name;
}


@Transient
public String getBackgroundColor(){
    String backgroundColor = "background-color:#32c24d !important;";
    if (!StringUtils.isBlank(this.theme) && this.theme.equals("01")) {
        backgroundColor = "background-color:#32c24d !important;";
    } else if (!StringUtils.isBlank(this.theme) && this.theme.equals("02")) {
        backgroundColor = "background-color:#373d41 !important;";
    } else if (!StringUtils.isBlank(this.theme) && this.theme.equals("03")) {
        backgroundColor = "background-image: -webkit-linear-gradient(right,#00c89d 0,#1E90FF 100%) !important;";
    }
    return backgroundColor;
}


public String getCc_acl(){
    return cc_acl;
}


public String getCc_quene(){
    return cc_quene;
}


public String getSmsworkordertp(){
    return smsworkordertp;
}


public String getSmscreatetp(){
    return smscreatetp;
}


public boolean isSmstocreater(){
    return smstocreater;
}


public void setCc_siptrunk(String cc_siptrunk){
    this.cc_siptrunk = cc_siptrunk;
}


public String getTitle(){
    return title;
}


public void setEmailtocreatertp(String emailtocreatertp){
    this.emailtocreatertp = emailtocreatertp;
}


public boolean isTenantconsole(){
    return tenantconsole;
}


public void setId(String id){
    this.id = id;
}


public String getIconstr(){
    return iconstr;
}


public boolean isWorkorders(){
    return workorders;
}


public void setCallout(boolean callout){
    this.callout = callout;
}


public String getEmailtocreatertp(){
    return emailtocreatertp;
}


public void setEnablevoice(boolean enablevoice){
    this.enablevoice = enablevoice;
}


public String getMailprocesstp(){
    return mailprocesstp;
}


public void setJksfile(String jksfile){
    this.jksfile = jksfile;
}


public String getJksfile(){
    return jksfile;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getLoglevel(){
    return loglevel;
}


public void setTitle(String title){
    this.title = title;
}


public String getConsolelogowidth(){
    return consolelogowidth;
}


public void setCc_callcenter(String cc_callcenter){
    this.cc_callcenter = cc_callcenter;
}


public String getEmailid(){
    return emailid;
}


public void setWorkorders(boolean workorders){
    this.workorders = workorders;
}


public void setEnablesms(boolean enablesms){
    this.enablesms = enablesms;
}


public void setSmsworkordertp(String smsworkordertp){
    this.smsworkordertp = smsworkordertp;
}


public String getLoginlogowidth(){
    return loginlogowidth;
}


public String getSmsprocesstp(){
    return smsprocesstp;
}


public void setFavlogo(String favlogo){
    this.favlogo = favlogo;
}


public boolean isEnabledis(){
    return enabledis;
}


public void setMailcreatetp(String mailcreatetp){
    this.mailcreatetp = mailcreatetp;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setCallcenter(boolean callcenter){
    this.callcenter = callcenter;
}


public void setIconstr(String iconstr){
    this.iconstr = iconstr;
}


public void setLoginlogowidth(String loginlogowidth){
    this.loginlogowidth = loginlogowidth;
}


public String getCc_siptrunk(){
    return cc_siptrunk;
}


public void setTenantconsole(boolean tenantconsole){
    this.tenantconsole = tenantconsole;
}


public String getConsolelogo(){
    return consolelogo;
}


public void setMailprocesstp(String mailprocesstp){
    this.mailprocesstp = mailprocesstp;
}


public String getLoginlogoheight(){
    return loginlogoheight;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public boolean isEmailtocreater(){
    return emailtocreater;
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


public boolean isEnablemail(){
    return enablemail;
}


public void setJkspassword(String jkspassword){
    this.jkspassword = jkspassword;
}


public String getDescription(){
    return description;
}


public String getFavlogo(){
    return favlogo;
}


public void setEmailshowrecipient(boolean emailshowrecipient){
    this.emailshowrecipient = emailshowrecipient;
}


public String getCc_ivr(){
    return cc_ivr;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getMapkey(){
    return mapkey;
}


public void setLoginlogoheight(String loginlogoheight){
    this.loginlogoheight = loginlogoheight;
}


public void setEmailtocreater(boolean emailtocreater){
    this.emailtocreater = emailtocreater;
}


@Transient
public String getColor(){
    String color = "color:#32c24d;";
    if (!StringUtils.isBlank(this.theme) && this.theme.equals("01")) {
        color = "color:#32c24d ";
    } else if (!StringUtils.isBlank(this.theme) && this.theme.equals("02")) {
        color = "color:#32c24d ";
    } else if (!StringUtils.isBlank(this.theme) && this.theme.equals("03")) {
        color = "color:#1E90FF ";
    }
    return color;
}


public boolean isEmailshowrecipient(){
    return emailshowrecipient;
}


public void setMemo(String memo){
    this.memo = memo;
}


public void setEnabledis(boolean enabledis){
    this.enabledis = enabledis;
}


public void setEnablemail(boolean enablemail){
    this.enablemail = enablemail;
}


public void setCc_acl(String cc_acl){
    this.cc_acl = cc_acl;
}


public String getOrgi(){
    return orgi;
}


public String getNamealias(){
    return namealias;
}


}