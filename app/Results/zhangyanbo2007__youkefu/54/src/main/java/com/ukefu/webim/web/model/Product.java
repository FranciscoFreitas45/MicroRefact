package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_sales_product")
@org.hibernate.annotations.Proxy(lazy = false)
public class Product {

 private  long serialVersionUID;

 private  String id;

 private  String title;

 private  String content;

 private  String keyword;

 private  String summary;

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

 private  double price;

 private  int quota;

 private  String url;

 private  String field1;

 private  String field2;

 private  String field3;

 private  String field4;

 private  String field5;

 private  String field6;

 private  String field7;

 private  String field8;

 private  String field9;

 private  String field10;

 private  String termtype;

 private  Date begintime;

 private  Date endtime;

 private  String provoice;


public void setTermtype(String termtype){
    this.termtype = termtype;
}


public String getStatus(){
    return status;
}


public void setPrice(double price){
    this.price = price;
}


public String getTitle(){
    return title;
}


public void setKeyword(String keyword){
    this.keyword = keyword;
}


public String getField10(){
    return field10;
}


public void setCate(String cate){
    this.cate = cate;
}


public void setId(String id){
    this.id = id;
}


public String getProvoice(){
    return provoice;
}


public void setProvoice(String provoice){
    this.provoice = provoice;
}


public void setTptype(String tptype){
    this.tptype = tptype;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setField1(String field1){
    this.field1 = field1;
}


public void setTitle(String title){
    this.title = title;
}


public void setField3(String field3){
    this.field3 = field3;
}


public void setField2(String field2){
    this.field2 = field2;
}


public void setQuota(int quota){
    this.quota = quota;
}


public void setField5(String field5){
    this.field5 = field5;
}


public void setField4(String field4){
    this.field4 = field4;
}


public void setField7(String field7){
    this.field7 = field7;
}


public void setUrl(String url){
    this.url = url;
}


public void setField6(String field6){
    this.field6 = field6;
}


public String getUrl(){
    return url;
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


public String getCate(){
    return cate;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setField10(String field10){
    this.field10 = field10;
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
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public int getQuota(){
    return quota;
}


public String getUsername(){
    return username;
}


public void setField9(String field9){
    this.field9 = field9;
}


public void setField8(String field8){
    this.field8 = field8;
}


public Date getCreatetime(){
    return createtime;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
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


public String getField8(){
    return field8;
}


public void setBegintime(Date begintime){
    this.begintime = begintime;
}


public String getField9(){
    return field9;
}


public String getTermtype(){
    return termtype;
}


public String getField4(){
    return field4;
}


public String getField5(){
    return field5;
}


public void setStatus(String status){
    this.status = status;
}


public double getPrice(){
    return price;
}


public String getField6(){
    return field6;
}


public String getField7(){
    return field7;
}


public String getKeyword(){
    return keyword;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getField1(){
    return field1;
}


public String getField2(){
    return field2;
}


public String getField3(){
    return field3;
}


public String getOrgi(){
    return orgi;
}


public Date getEndtime(){
    return endtime;
}


}