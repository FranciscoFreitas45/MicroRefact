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
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;
import com.ukefu.util.UKTools;
@Document(indexName = "uckefu", type = "uk_orderscomment")
@Entity
@Table(name = "uk_orderscomment")
@org.hibernate.annotations.Proxy(lazy = false)
public class OrdersComment implements UKAgg{

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

 private  boolean prirep;

 private  int up;

 private  int comments;

 private  boolean admin;

 private  boolean datastatus;

 private  String orgi;

 private  String cate;

 private  String optype;

 private  String approval;

 private  String retback;

 private  String accdept;

 private  String accuser;

 private  String ipcode;

 private  String country;

 private  String province;

 private  String city;

 private  String isp;

 private  String region;

 private  int rowcount;

 private  String key;

 private  User user;


public void setCountry(String country){
    this.country = country;
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


public void setAccdept(String accdept){
    this.accdept = accdept;
}


public void setOptype(String optype){
    this.optype = optype;
}


public void setDatastatus(boolean datastatus){
    this.datastatus = datastatus;
}


public String getAccdept(){
    return accdept;
}


public boolean isOptimal(){
    return optimal;
}


public void setRowcount(int rowcount){
    this.rowcount = rowcount;
}


public String getIpcode(){
    return ipcode;
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


public void setUser(User user){
    this.user = user;
}


public void setAccuser(String accuser){
    this.accuser = accuser;
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


public boolean isPrirep(){
    return prirep;
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


public String getRegion(){
    return region;
}


public String getAccuser(){
    return accuser;
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


@Transient
public int getRowcount(){
    return rowcount;
}


public void setContent(String content){
    this.content = content;
}


public void setIpcode(String ipcode){
    this.ipcode = ipcode;
}


public void setIsp(String isp){
    this.isp = isp;
}


public String getApproval(){
    return approval;
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


public int getUp(){
    return up;
}


public String getUsername(){
    return username;
}


public Date getCreatetime(){
    return createtime;
}


public String getProvince(){
    return province;
}


public String getIsp(){
    return isp;
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


public void setRegion(String region){
    this.region = region;
}


public void setUsername(String username){
    this.username = username;
}


public void setRetback(String retback){
    this.retback = retback;
}


public String getRetback(){
    return retback;
}


public String getOptype(){
    return optype;
}


public String getDataid(){
    return dataid;
}


public String getOrgi(){
    return orgi;
}


public void setPrirep(boolean prirep){
    this.prirep = prirep;
}


public boolean isDatastatus(){
    return datastatus;
}


public void setApproval(String approval){
    this.approval = approval;
}


}