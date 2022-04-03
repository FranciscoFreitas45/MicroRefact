package DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";


public String getCc_router(){
    return cc_router;
}


public String getMailcreatetp(){
    return mailcreatetp;
}


public String getCode(){
    return code;
}


public String getCc_extention(){
    return cc_extention;
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


public String getMemo(){
    return memo;
}


public String getJkspassword(){
    return jkspassword;
}


public String getSmsupdatetp(){
    return smsupdatetp;
}


public String getWhitelistip(){
    return whitelistip;
}


public Date getCreatetime(){
    return createtime;
}


public String getSmsid(){
    return smsid;
}


public String getCc_callcenter(){
    return cc_callcenter;
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


public String getTitle(){
    return title;
}


public String getIconstr(){
    return iconstr;
}


public String getEmailtocreatertp(){
    return emailtocreatertp;
}


public String getMailprocesstp(){
    return mailprocesstp;
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


public String getConsolelogowidth(){
    return consolelogowidth;
}


public String getEmailid(){
    return emailid;
}


public String getLoginlogowidth(){
    return loginlogowidth;
}


public String getSmsprocesstp(){
    return smsprocesstp;
}


public String getCc_siptrunk(){
    return cc_siptrunk;
}


public String getConsolelogo(){
    return consolelogo;
}


public String getLoginlogoheight(){
    return loginlogoheight;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public String getDescription(){
    return description;
}


public String getFavlogo(){
    return favlogo;
}


public String getCc_ivr(){
    return cc_ivr;
}


public String getCreater(){
    return creater;
}


public String getMapkey(){
    return mapkey;
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


public String getOrgi(){
    return orgi;
}


public String getNamealias(){
    return namealias;
}


public boolean isEnablessl(){
    return enablessl;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isEnablessl"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public void setLoglevel(String loglevel){
    this.loglevel = loglevel;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setLoglevel"))

.queryParam("loglevel",loglevel)
;
restTemplate.put(builder.toUriString(),null);
}


public boolean isSavelog(){
    return savelog;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isSavelog"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public boolean isEnabletneant(){
    return enabletneant;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isEnabletneant"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public boolean isTenantshare(){
    return tenantshare;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isTenantshare"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public boolean isTenantconsole(){
    return tenantconsole;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isTenantconsole"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}