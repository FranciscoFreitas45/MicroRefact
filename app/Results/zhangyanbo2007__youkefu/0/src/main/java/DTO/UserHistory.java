package DTO;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://9";


public String getMaintype(){
    return maintype;
}


public String getName(){
    return name;
}


public String getCountry(){
    return country;
}


public String getReferer(){
    return referer;
}


public String getTitle(){
    return title;
}


public String getOstype(){
    return ostype;
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


public Date getUpdatetime(){
    return updatetime;
}


public String getUrl(){
    return url;
}


public String getRegion(){
    return region;
}


public String getSubtype(){
    return subtype;
}


public String getBrowser(){
    return browser;
}


public String getCreatedate(){
    return createdate;
}


public String getIp(){
    return ip;
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


public String getHostname(){
    return hostname;
}


public Date getCreatetime(){
    return createtime;
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


public String getSessionid(){
    return sessionid;
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


public void setUrl(String url){
    this.url = url;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUrl"))

.queryParam("url",url)
;
restTemplate.put(builder.toUriString(),null);
}


public void setReferer(String referer){
    this.referer = referer;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setReferer"))

.queryParam("referer",referer)
;
restTemplate.put(builder.toUriString(),null);
}


public void setParam(String param){
    this.param = param;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setParam"))

.queryParam("param",param)
;
restTemplate.put(builder.toUriString(),null);
}


public void setMaintype(String maintype){
    this.maintype = maintype;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setMaintype"))

.queryParam("maintype",maintype)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSubtype(String subtype){
    this.subtype = subtype;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSubtype"))

.queryParam("subtype",subtype)
;
restTemplate.put(builder.toUriString(),null);
}


public void setName(String name){
    this.name = name;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setName"))

.queryParam("name",name)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAdmin(boolean admin){
    this.admin = admin;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAdmin"))

.queryParam("admin",admin)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAccessnum(boolean accessnum){
    this.accessnum = accessnum;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAccessnum"))

.queryParam("accessnum",accessnum)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCreater(String creater){
    this.creater = creater;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCreater"))

.queryParam("creater",creater)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUsername(String username){
    this.username = username;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUsername"))

.queryParam("username",username)
;
restTemplate.put(builder.toUriString(),null);
}


public void setOrgi(String orgi){
    this.orgi = orgi;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOrgi"))

.queryParam("orgi",orgi)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTitle(String title){
    this.title = title;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTitle"))

.queryParam("title",title)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAppid(String appid){
    this.appid = appid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAppid"))

.queryParam("appid",appid)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSessionid(String sessionid){
    this.sessionid = sessionid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSessionid"))

.queryParam("sessionid",sessionid)
;
restTemplate.put(builder.toUriString(),null);
}


public void setHostname(String hostname){
    this.hostname = hostname;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setHostname"))

.queryParam("hostname",hostname)
;
restTemplate.put(builder.toUriString(),null);
}


public void setIp(String ip){
    this.ip = ip;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setIp"))

.queryParam("ip",ip)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCountry(String country){
    this.country = country;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCountry"))

.queryParam("country",country)
;
restTemplate.put(builder.toUriString(),null);
}


public void setProvince(String province){
    this.province = province;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setProvince"))

.queryParam("province",province)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCity(String city){
    this.city = city;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCity"))

.queryParam("city",city)
;
restTemplate.put(builder.toUriString(),null);
}


public void setIsp(String isp){
    this.isp = isp;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setIsp"))

.queryParam("isp",isp)
;
restTemplate.put(builder.toUriString(),null);
}


public void setOstype(String ostype){
    this.ostype = ostype;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOstype"))

.queryParam("ostype",ostype)
;
restTemplate.put(builder.toUriString(),null);
}


public void setBrowser(String browser){
    this.browser = browser;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setBrowser"))

.queryParam("browser",browser)
;
restTemplate.put(builder.toUriString(),null);
}


public void setMobile(String mobile){
    this.mobile = mobile;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setMobile"))

.queryParam("mobile",mobile)
;
restTemplate.put(builder.toUriString(),null);
}


}