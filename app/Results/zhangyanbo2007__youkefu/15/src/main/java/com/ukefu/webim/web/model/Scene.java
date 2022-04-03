package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import com.ukefu.util.UKTools;
@Entity
@Table(name = "uk_xiaoe_scene")
@org.hibernate.annotations.Proxy(lazy = false)
public class Scene {

 private  long serialVersionUID;

 private  String id;

 private  String sessionid;

 private  String title;

 private  String content;

 private  float price;

 private  String keyword;

 private  String summary;

 private  boolean anonymous;

 private  Date begintime;

 private  Date endtime;

 private  boolean top;

 private  boolean essence;

 private  boolean accept;

 private  boolean finish;

 private  String replaytype;

 private  boolean allowask;

 private  String inputcon;

 private  String outputcon;

 private  String userinput;

 private  String aireply;

 private  int answers;

 private  int views;

 private  int followers;

 private  int collections;

 private  int comments;

 private  boolean frommobile;

 private  String status;

 private  String tptype;

 private  String cate;

 private  String username;

 private  String orgi;

 private  String creater;

 private  Date createtime;

 private  Date updatetime;

 private  String memo;

 private  String organ;


public void setReplaytype(String replaytype){
    this.replaytype = replaytype;
}


public boolean isAccept(){
    return accept;
}


public String getInputcon(){
    return inputcon;
}


public int getCollections(){
    return collections;
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


public boolean isAllowask(){
    return allowask;
}


public void setSessionid(String sessionid){
    this.sessionid = sessionid;
}


public void setKeyword(String keyword){
    this.keyword = keyword;
}


public void setUserinput(String userinput){
    this.userinput = userinput;
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


public String getUserinput(){
    return userinput;
}


public void setAireply(String aireply){
    this.aireply = aireply;
}


public String getAireply(){
    return aireply;
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


public String getOutputcon(){
    return outputcon;
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


public String getOrgan(){
    return organ;
}


public boolean isTop(){
    return top;
}


public void setAllowask(boolean allowask){
    this.allowask = allowask;
}


public String getCate(){
    return cate;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setFollowers(int followers){
    this.followers = followers;
}


public void setFinish(boolean finish){
    this.finish = finish;
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


public boolean isEssence(){
    return essence;
}


public String getUsername(){
    return username;
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


public void setInputcon(String inputcon){
    this.inputcon = inputcon;
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


public String getReplaytype(){
    return replaytype;
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


public void setOutputcon(String outputcon){
    this.outputcon = outputcon;
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


@Column(name = "sviews")
public int getViews(){
    return views;
}


public void setViews(int views){
    this.views = views;
}


public String getOrgi(){
    return orgi;
}


public Date getEndtime(){
    return endtime;
}


public void setAnonymous(boolean anonymous){
    this.anonymous = anonymous;
}


public void setAccept(boolean accept){
    this.accept = accept;
}


}