package DTO;
 import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
public class AgentUserContacts implements Serializable{

 private  long serialVersionUID;

 private  String id;

 private  String username;

 private  String creater;

 private  Date createtime;

 private  String orgi;

 private  String contactsid;

 private  String userid;

 private  String appid;

 private  String channel;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


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


public void setChannel(String channel){
    this.channel = channel;
}


public String getUsername(){
    return username;
}


public String getAppid(){
    return appid;
}


public String getContactsid(){
    return contactsid;
}


public Date getCreatetime(){
    return createtime;
}


public String getChannel(){
    return channel;
}


public String getOrgi(){
    return orgi;
}


public void setCreater(String creater){
    this.creater = creater;
}


public String getCreater(){
    return creater;
}


public String getUserid(){
    return userid;
}


public void setAppid(String appid){
    this.appid = appid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAppid"))

.queryParam("appid",appid)
;
restTemplate.put(builder.toUriString(),null);
}


public void setContactsid(String contactsid){
    this.contactsid = contactsid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setContactsid"))

.queryParam("contactsid",contactsid)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUserid(String userid){
    this.userid = userid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUserid"))

.queryParam("userid",userid)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCreatetime"))

.queryParam("createtime",createtime)
;
restTemplate.put(builder.toUriString(),null);
}


}