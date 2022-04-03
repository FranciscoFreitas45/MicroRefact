package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;
import com.ukefu.util.UKTools;
import Interface.Topic;
import Interface.User;
@Document(indexName = "uckefu", type = "uc_kbs_topiccomment")
public class KbsTopicComment implements UKAgg{

 private  long serialVersionUID;

 private  String id;

 private  String username;

 private  String creater;

 private  Date createtime;

@Field(index = FieldIndex.not_analyzed, type = FieldType.String)
 private  String dataid;

 private  String content;

 private  Date updatetime;

 private  boolean optimal;

 private  int up;

 private  int comments;

 private  boolean admin;

 private  String cate;

 private  String optype;

 private  String ipcode;

 private  String country;

 private  String province;

 private  String city;

 private  String isp;

 private  String region;

 private  int rowcount;

 private  String key;

 private  Topic topic;

 private  User user;


@Transient
public int getRowcount(){
    return rowcount;
}


public void setCountry(String country){
    this.country = country;
}


public void setContent(String content){
    this.content = content;
}


public void setIpcode(String ipcode){
    this.ipcode = ipcode;
}


public String getCountry(){
    return country;
}


public void setProvince(String province){
    this.province = province;
}


public void setOptimal(boolean optimal){
    this.optimal = optimal;
}


public void setIsp(String isp){
    this.isp = isp;
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


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public void setOptype(String optype){
    this.optype = optype;
}


public int getUp(){
    return up;
}


@Transient
public Topic getTopic(){
    return topic;
}


public String getUsername(){
    return username;
}


public boolean isOptimal(){
    return optimal;
}


public void setRowcount(int rowcount){
    this.rowcount = rowcount;
}


public Date getCreatetime(){
    return createtime;
}


public String getIpcode(){
    return ipcode;
}


public String getProvince(){
    return province;
}


public String getIsp(){
    return isp;
}


public int getComments(){
    return comments;
}


public void setId(String id){
    this.id = id;
}


public void setUp(int up){
    this.up = up;
}


public void setCate(String cate){
    this.cate = cate;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public void setComments(int comments){
    this.comments = comments;
}


public void setUser(User user){
    this.user = user;
}


public String getCity(){
    return city;
}


public void setKey(String key){
    this.key = key;
}


@Transient
public String getKey(){
    return key;
}


public void setRegion(String region){
    this.region = region;
}


public void setUsername(String username){
    this.username = username;
}


public void setCity(String city){
    this.city = city;
}


public Date getUpdatetime(){
    return updatetime;
}


public boolean isAdmin(){
    return admin;
}


public void setTopic(Topic topic){
    this.topic = topic;
}


public String getOptype(){
    return optype;
}


public String getDataid(){
    return dataid;
}


public String getRegion(){
    return region;
}


public String getCate(){
    return cate;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setDataid(String dataid){
    this.dataid = dataid;
}


public void setAdmin(boolean admin){
    this.admin = admin;
}


}