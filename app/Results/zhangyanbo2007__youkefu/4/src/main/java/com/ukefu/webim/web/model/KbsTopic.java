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
@Document(indexName = "uckefu", type = "uk_kbs_topic")
@Entity
@Table(name = "uk_kbs_topic")
@org.hibernate.annotations.Proxy(lazy = false)
public class KbsTopic extends ESBeanimplements UKAgg{

 private  long serialVersionUID;

 private  String id;

 private  String sessionid;

 private  String title;

 private  String content;

 private  String weixin;

 private  String email;

 private  String sms;

 private  String tts;

 private  float price;

 private  String keyword;

 private  String summary;

 private  String tags;

 private  boolean anonymous;

 private  boolean datastatus;

 private  boolean approval;

 private  Date begintime;

 private  Date endtime;

 private  boolean top;

 private  boolean essence;

 private  boolean accept;

 private  boolean finish;

 private  int answers;

@Column(name = "sviews")
 private  int views;

 private  int followers;

 private  int collections;

 private  int comments;

 private  boolean frommobile;

 private  String status;

 private  String tptype;

 private  String cate;

 private  String attachment;

 private  String username;

 private  String orgi;

 private  String creater;

 private  Date createtime;

 private  Date updatetime;

 private  String memo;

 private  String organ;

 private  int rowcount;

 private  String key;

 private  User user;


public boolean isAccept(){
    return accept;
}


public int getCollections(){
    return collections;
}


public String getTags(){
    return tags;
}


public void setDatastatus(boolean datastatus){
    this.datastatus = datastatus;
}


public void setPrice(float price){
    this.price = price;
}


public String getStatus(){
    return status;
}


public boolean isFrommobile(){
    return frommobile;
}


public void setAnswers(int answers){
    this.answers = answers;
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


public void setSessionid(String sessionid){
    this.sessionid = sessionid;
}


public void setKeyword(String keyword){
    this.keyword = keyword;
}


public void setSms(String sms){
    this.sms = sms;
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


public void setUser(User user){
    this.user = user;
}


public void setKey(String key){
    this.key = key;
}


@Transient
public String getKey(){
    return key;
}


public String getAttachment(){
    return attachment;
}


public void setTptype(String tptype){
    this.tptype = tptype;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setTitle(String title){
    this.title = title;
}


public String getSms(){
    return sms;
}


public String getWeixin(){
    return weixin;
}


public boolean isAnonymous(){
    return anonymous;
}


public String getMemo(){
    return memo;
}


public void setOrgan(String organ){
    this.organ = organ;
}


public void setTts(String tts){
    this.tts = tts;
}


public String getOrgan(){
    return organ;
}


public void setEmail(String email){
    this.email = email;
}


public String getTts(){
    return tts;
}


public boolean isTop(){
    return top;
}


public String getCate(){
    return cate;
}


public void setCreater(String creater){
    this.creater = creater;
}


public String getEmail(){
    return email;
}


public void setFollowers(int followers){
    this.followers = followers;
}


public void setFinish(boolean finish){
    this.finish = finish;
}


@Transient
public int getRowcount(){
    return rowcount;
}


public void setContent(String content){
    this.content = content;
}


public String getTptype(){
    return tptype;
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


public Date getBegintime(){
    return begintime;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "assigned")
public String getId(){
    return id;
}


public int getFollowers(){
    return followers;
}


public void setTags(String tags){
    this.tags = tags;
}


public boolean isEssence(){
    return essence;
}


public String getUsername(){
    return username;
}


public boolean isApproval(){
    return approval;
}


public Date getCreatetime(){
    return createtime;
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


public void setSummary(String summary){
    this.summary = summary;
}


public String getSummary(){
    return summary;
}


public void setUsername(String username){
    this.username = username;
}


public void setEndtime(Date endtime){
    this.endtime = endtime;
}


public void setCollections(int collections){
    this.collections = collections;
}


public void setBegintime(Date begintime){
    this.begintime = begintime;
}


public boolean isFinish(){
    return finish;
}


public float getPrice(){
    return price;
}


public void setStatus(String status){
    this.status = status;
}


public String getKeyword(){
    return keyword;
}


public void setMemo(String memo){
    this.memo = memo;
}


public int getAnswers(){
    return answers;
}


public int getViews(){
    return views;
}


public void setViews(int views){
    this.views = views;
}


public String getOrgi(){
    return orgi;
}


public void setWeixin(String weixin){
    this.weixin = weixin;
}


public void setAttachment(String attachment){
    this.attachment = attachment;
}


public Date getEndtime(){
    return endtime;
}


public boolean isDatastatus(){
    return datastatus;
}


public void setApproval(boolean approval){
    this.approval = approval;
}


public void setAnonymous(boolean anonymous){
    this.anonymous = anonymous;
}


public void setAccept(boolean accept){
    this.accept = accept;
}


}