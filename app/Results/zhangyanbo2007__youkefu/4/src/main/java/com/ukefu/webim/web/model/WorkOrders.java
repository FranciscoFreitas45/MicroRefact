package com.ukefu.webim.web.model;
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
@Document(indexName = "uckefu", type = "uk_workorders", createIndex = false)
@Entity
@Table(name = "uk_workorders")
@org.hibernate.annotations.Proxy(lazy = false)
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


public void setInitiator(String initiator){
    this.initiator = initiator;
}


public String getQualitytype(){
    return qualitytype;
}


public void setShares(String shares){
    this.shares = shares;
}


public int getCollections(){
    return collections;
}


public void setTemplateid(String templateid){
    this.templateid = templateid;
}


public String getTags(){
    return tags;
}


public void setDatastatus(boolean datastatus){
    this.datastatus = datastatus;
}


public String getStatus(){
    return status;
}


public void setCusid(String cusid){
    this.cusid = cusid;
}


public void setAnswers(int answers){
    this.answers = answers;
}


public String getQualitydisuser(){
    return qualitydisuser;
}


public void setCurrentorgan(Organ currentorgan){
    this.currentorgan = currentorgan;
}


public Date getQualitydistime(){
    return qualitydistime;
}


public void setSessionid(String sessionid){
    this.sessionid = sessionid;
}


public void setAni(String ani){
    this.ani = ani;
}


public void setQualitytype(String qualitytype){
    this.qualitytype = qualitytype;
}


public void setUser(User user){
    this.user = user;
}


public void setAccuser(String accuser){
    this.accuser = accuser;
}


public String getCusid(){
    return cusid;
}


public void setQualitydisuser(String qualitydisuser){
    this.qualitydisuser = qualitydisuser;
}


public void setQualitypass(int qualitypass){
    this.qualitypass = qualitypass;
}


public void setQualitydistime(Date qualitydistime){
    this.qualitydistime = qualitydistime;
}


public int getQualityscore(){
    return qualityscore;
}


public String getAni(){
    return ani;
}


public boolean isAnonymous(){
    return anonymous;
}


public String getMemo(){
    return memo;
}


public String getAccuser(){
    return accuser;
}


public boolean isTop(){
    return top;
}


public String getQualitystatus(){
    return qualitystatus;
}


public void setDataid(String dataid){
    this.dataid = dataid;
}


public void setPriority(String priority){
    this.priority = priority;
}


public void setBpmid(String bpmid){
    this.bpmid = bpmid;
}


public String getEventid(){
    return eventid;
}


@Transient
public int getRowcount(){
    return rowcount;
}


public void setEventid(String eventid){
    this.eventid = eventid;
}


public void setContent(String content){
    this.content = content;
}


public String getAssuser(){
    return assuser;
}


public String getQualityactid(){
    return qualityactid;
}


public void setAssuser(String assuser){
    this.assuser = assuser;
}


public int getFollowers(){
    return followers;
}


public boolean isEssence(){
    return essence;
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


public void setFav(Favorites fav){
    this.fav = fav;
}


public void setSummary(String summary){
    this.summary = summary;
}


public String getSummary(){
    return summary;
}


public void setQualityuser(String qualityuser){
    this.qualityuser = qualityuser;
}


public void setQualitytime(Date qualitytime){
    this.qualitytime = qualitytime;
}


public void setCollections(int collections){
    this.collections = collections;
}


@Transient
public String getOrderid(){
    return orderid;
}


public void setContacts(Contacts contacts){
    this.contacts = contacts;
}


public String getAgent(){
    return agent;
}


public boolean isFinish(){
    return finish;
}


public void setOrderid(String orderid){
    this.orderid = orderid;
}


@Transient
public Favorites getFav(){
    return fav;
}


public float getPrice(){
    return price;
}


public void setAgent(String agent){
    this.agent = agent;
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


public void setAccept(boolean accept){
    this.accept = accept;
}


public String getOrderno(){
    return orderno;
}


public String getQualitydisorgan(){
    return qualitydisorgan;
}


public boolean isAccept(){
    return accept;
}


public void setOrderno(String orderno){
    this.orderno = orderno;
}


public void setAccdept(String accdept){
    this.accdept = accdept;
}


public String getAccdept(){
    return accdept;
}


public void setPrice(float price){
    this.price = price;
}


public boolean isFrommobile(){
    return frommobile;
}


public Date getQualitytime(){
    return qualitytime;
}


public void setTop(boolean top){
    this.top = top;
}


public String getTitle(){
    return title;
}


public void setRowcount(int rowcount){
    this.rowcount = rowcount;
}


public String getQualityuser(){
    return qualityuser;
}


public void setKeyword(String keyword){
    this.keyword = keyword;
}


public int getComments(){
    return comments;
}


public void setId(String id){
    this.id = id;
}


public void setCate(String cate){
    this.cate = cate;
}


public void setQualityactid(String qualityactid){
    this.qualityactid = qualityactid;
}


public void setKey(String key){
    this.key = key;
}


@Transient
public String getKey(){
    return key;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setQualityfilterid(String qualityfilterid){
    this.qualityfilterid = qualityfilterid;
}


public void setTitle(String title){
    this.title = title;
}


public void setTaskid(String taskid){
    this.taskid = taskid;
}


public void setQualityorgan(String qualityorgan){
    this.qualityorgan = qualityorgan;
}


public void setOrgan(String organ){
    this.organ = organ;
}


public boolean isAssigned(){
    return assigned;
}


public String getBpmid(){
    return bpmid;
}


public void setQualitydistype(String qualitydistype){
    this.qualitydistype = qualitydistype;
}


public String getOrgan(){
    return organ;
}


public String getCate(){
    return cate;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setWotype(String wotype){
    this.wotype = wotype;
}


public void setFollowers(int followers){
    this.followers = followers;
}


@Transient
public User getCurrent(){
    return current;
}


public void setSkill(String skill){
    this.skill = skill;
}


public String getQualitydistype(){
    return qualitydistype;
}


public String getSkill(){
    return skill;
}


public void setFinish(boolean finish){
    this.finish = finish;
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


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
public String getId(){
    return id;
}


public void setTags(String tags){
    this.tags = tags;
}


@Transient
public Contacts getContacts(){
    return contacts;
}


@Transient
public Organ getCurrentorgan(){
    return currentorgan;
}


public void setAssigned(boolean assigned){
    this.assigned = assigned;
}


public void setFrommobile(boolean frommobile){
    this.frommobile = frommobile;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public void setEssence(boolean essence){
    this.essence = essence;
}


@Transient
public String getSessionid(){
    return sessionid;
}


public void setComments(int comments){
    this.comments = comments;
}


public void setCurrent(User current){
    this.current = current;
}


public int getQualitypass(){
    return qualitypass;
}


public void setUsername(String username){
    this.username = username;
}


public void setQualitydisorgan(String qualitydisorgan){
    this.qualitydisorgan = qualitydisorgan;
}


public String getQualityorgan(){
    return qualityorgan;
}


public void setStatus(String status){
    this.status = status;
}


public String getInitiator(){
    return initiator;
}


public String getKeyword(){
    return keyword;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getTemplateid(){
    return templateid;
}


@Column(name = "sviews")
public int getViews(){
    return views;
}


public void setQualitystatus(String qualitystatus){
    this.qualitystatus = qualitystatus;
}


public void setViews(int views){
    this.views = views;
}


public String getOrgi(){
    return orgi;
}


public void setQualityscore(int qualityscore){
    this.qualityscore = qualityscore;
}


public String getQualityfilterid(){
    return qualityfilterid;
}


public boolean isDatastatus(){
    return datastatus;
}


public void setAnonymous(boolean anonymous){
    this.anonymous = anonymous;
}


}