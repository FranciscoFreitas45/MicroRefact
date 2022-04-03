package DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;
import com.ukefu.util.UKTools;
public class WorkOrders extends ESBeanimplements UKAgg{

 private  long serialVersionUID;

 private  String id;

 private  String orderno;

 private  String sessionid;

 private  String title;

 private  String content;

 private  float price;

 private  String keyword;

 private  String summary;

 private  boolean anonymous;

 private  boolean top;

 private  boolean essence;

 private  boolean accept;

 private  boolean finish;

 private  int answers;

 private  int views;

 private  int followers;

 private  int collections;

 private  int comments;

 private  boolean frommobile;

 private  String status;

 private  String wotype;

 private  boolean datastatus;

 private  String taskid;

 private  String orderid;

 private  String dataid;

 private  String eventid;

 private  String ani;

 private  String cate;

 private  String priority;

 private  Contacts contacts;

 private  String cusid;

 private  String initiator;

 private  String bpmid;

 private  String tags;

 private  String accdept;

 private  String accuser;

 private  boolean assigned;

 private  String username;

 private  String orgi;

 private  String creater;

 private  Date createtime;

 private  Date updatetime;

 private  String memo;

 private  String organ;

 private  String agent;

 private  String shares;

 private  String skill;

 private  int rowcount;

 private  String key;

 private  User user;

 private  User current;

 private  Organ currentorgan;

 private  Favorites fav;

 private  String qualitystatus;

 private  String qualitydisorgan;

 private  String qualitydisuser;

 private  Date qualitydistime;

 private  String assuser;

 private  String templateid;

 private  String qualitydistype;

 private  String qualityorgan;

 private  String qualityuser;

 private  int qualityscore;

 private  Date qualitytime;

 private  String qualitytype;

 private  String qualityactid;

 private  String qualityfilterid;

 private  int qualitypass;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public String getQualitytype(){
    return qualitytype;
}


public int getCollections(){
    return collections;
}


public String getTags(){
    return tags;
}


public String getStatus(){
    return status;
}


public String getQualitydisuser(){
    return qualitydisuser;
}


public Date getQualitydistime(){
    return qualitydistime;
}


public String getCusid(){
    return cusid;
}


public int getQualityscore(){
    return qualityscore;
}


public String getAni(){
    return ani;
}


public String getMemo(){
    return memo;
}


public String getAccuser(){
    return accuser;
}


public String getQualitystatus(){
    return qualitystatus;
}


public String getEventid(){
    return eventid;
}


@Transient
public int getRowcount(){
    return rowcount;
}


public String getAssuser(){
    return assuser;
}


public String getQualityactid(){
    return qualityactid;
}


public int getFollowers(){
    return followers;
}


public String getUsername(){
    return username;
}


public String getShares(){
    return shares;
}


public Date getCreatetime(){
    return createtime;
}


public String getSummary(){
    return summary;
}


@Transient
public String getOrderid(){
    return orderid;
}


public String getAgent(){
    return agent;
}


@Transient
public Favorites getFav(){
    return fav;
}


public float getPrice(){
    return price;
}


public String getWotype(){
    return wotype;
}


public String getDataid(){
    return dataid;
}


public int getAnswers(){
    return answers;
}


public String getPriority(){
    return priority;
}


public String getOrderno(){
    return orderno;
}


public String getQualitydisorgan(){
    return qualitydisorgan;
}


public String getAccdept(){
    return accdept;
}


public Date getQualitytime(){
    return qualitytime;
}


public String getTitle(){
    return title;
}


public String getQualityuser(){
    return qualityuser;
}


public int getComments(){
    return comments;
}


@Transient
public String getKey(){
    return key;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getBpmid(){
    return bpmid;
}


public String getOrgan(){
    return organ;
}


public String getCate(){
    return cate;
}


@Transient
public User getCurrent(){
    return current;
}


public String getQualitydistype(){
    return qualitydistype;
}


public String getSkill(){
    return skill;
}


@Transient
public String getTaskid(){
    return taskid;
}


public String getContent(){
    return content;
}


@Transient
public User getUser(){
    return user;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
public String getId(){
    return id;
}


@Transient
public Contacts getContacts(){
    return contacts;
}


@Transient
public Organ getCurrentorgan(){
    return currentorgan;
}


public String getCreater(){
    return creater;
}


@Transient
public String getSessionid(){
    return sessionid;
}


public int getQualitypass(){
    return qualitypass;
}


public String getQualityorgan(){
    return qualityorgan;
}


public String getInitiator(){
    return initiator;
}


public String getKeyword(){
    return keyword;
}


public String getTemplateid(){
    return templateid;
}


@Column(name = "sviews")
public int getViews(){
    return views;
}


public String getOrgi(){
    return orgi;
}


public String getQualityfilterid(){
    return qualityfilterid;
}


public void setQualitydistime(Date qualitydistime){
    this.qualitydistime = qualitydistime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setQualitydistime"))

.queryParam("qualitydistime",qualitydistime)
;
restTemplate.put(builder.toUriString(),null);
}


public void setQualitytype(String qualitytype){
    this.qualitytype = qualitytype;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setQualitytype"))

.queryParam("qualitytype",qualitytype)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAssuser(String assuser){
    this.assuser = assuser;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAssuser"))

.queryParam("assuser",assuser)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTemplateid(String templateid){
    this.templateid = templateid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTemplateid"))

.queryParam("templateid",templateid)
;
restTemplate.put(builder.toUriString(),null);
}


public void setQualitystatus(String qualitystatus){
    this.qualitystatus = qualitystatus;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setQualitystatus"))

.queryParam("qualitystatus",qualitystatus)
;
restTemplate.put(builder.toUriString(),null);
}


public void setQualityactid(String qualityactid){
    this.qualityactid = qualityactid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setQualityactid"))

.queryParam("qualityactid",qualityactid)
;
restTemplate.put(builder.toUriString(),null);
}


public void setQualityfilterid(String qualityfilterid){
    this.qualityfilterid = qualityfilterid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setQualityfilterid"))

.queryParam("qualityfilterid",qualityfilterid)
;
restTemplate.put(builder.toUriString(),null);
}


public void setQualitydisorgan(String qualitydisorgan){
    this.qualitydisorgan = qualitydisorgan;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setQualitydisorgan"))

.queryParam("qualitydisorgan",qualitydisorgan)
;
restTemplate.put(builder.toUriString(),null);
}


public void setQualitydistype(String qualitydistype){
    this.qualitydistype = qualitydistype;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setQualitydistype"))

.queryParam("qualitydistype",qualitydistype)
;
restTemplate.put(builder.toUriString(),null);
}


public void setQualitydisuser(String qualitydisuser){
    this.qualitydisuser = qualitydisuser;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setQualitydisuser"))

.queryParam("qualitydisuser",qualitydisuser)
;
restTemplate.put(builder.toUriString(),null);
}


}