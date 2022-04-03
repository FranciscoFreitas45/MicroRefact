package com.ukefu.webim.web.model;
 import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import com.ukefu.util.UKTools;
@Entity
@Table(name = "uk_historyreport")
@org.hibernate.annotations.Proxy(lazy = false)
public class Reporter {

 private  long serialVersionUID;

 private  String dataid;

 private  String title;

 private  String id;

 private  String status;

 private  String amount;

 private  long pages;

 private  long total;

 private  int errors;

 private  long bytes;

 private  int threads;

 private  String type;

 private  int filter;

 private  Date createtime;

 private  Date starttime;

 private  Date endtime;

 private  String errormsg;

 private  String detailmsg;

 private  String orgi;

 private  String tableid;

 private  String tabledirid;

 private  String userid;

 private  String username;

 private  boolean error;

 private  boolean out;

 private  boolean round;

 private  long start;

 private  AtomicInteger atompages;

 private  String organ;


public void setPages(long pages){
    this.pages = pages;
}


@Transient
public long getStart(){
    return start;
}


public String getTabledirid(){
    return tabledirid;
}


public void setStarttime(Date starttime){
    this.starttime = starttime;
}


@Transient
public String getDetailmsg(){
    return detailmsg;
}


public void setTableid(String tableid){
    this.tableid = tableid;
}


public String getStatus(){
    return status;
}


public int getErrors(){
    return errors;
}


public long getBytes(){
    return bytes;
}


public void setOut(boolean out){
    this.out = out;
}


public String getTableid(){
    return tableid;
}


public String getTitle(){
    return title;
}


public void setStart(long start){
    this.start = start;
}


public void setThreads(int threads){
    this.threads = threads;
}


public void setId(String id){
    this.id = id;
}


public String getAmount(){
    return amount;
}


public void setTabledirid(String tabledirid){
    this.tabledirid = tabledirid;
}


public void setTitle(String title){
    this.title = title;
}


public void setFilter(int filter){
    this.filter = filter;
}


public void setErrormsg(String errormsg){
    this.errormsg = errormsg;
}


public void setAtompages(AtomicInteger atompages){
    this.atompages = atompages;
}


public void setOrgan(String organ){
    this.organ = organ;
}


public String getOrgan(){
    return organ;
}


public String getType(){
    return type;
}


public void setError(boolean error){
    this.error = error;
}


@Transient
public double getSpeed(){
    long times = (this.endtime.getTime() - start) / 1000;
    return times != 0 ? this.atompages.intValue() / times : this.atompages.intValue();
}


@Transient
public boolean isOut(){
    return out;
}


public void setDataid(String dataid){
    this.dataid = dataid;
}


public long getTotal(){
    return total;
}


public void setTotal(long total){
    this.total = total;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "assigned")
public String getId(){
    return id;
}


@Transient
public long getBytespeed(){
    long times = (this.endtime.getTime() - start) / 1000;
    return times != 0 ? this.bytes / times : this.bytes;
}


public void setErrors(int errors){
    this.errors = errors;
}


public String getUsername(){
    return username;
}


@Transient
public AtomicInteger getAtompages(){
    return atompages;
}


@Transient
public boolean isError(){
    return error;
}


public Date getCreatetime(){
    return createtime;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public void setAmount(String amount){
    this.amount = amount;
}


public String getUserid(){
    return userid;
}


public void setUserid(String userid){
    this.userid = userid;
}


public void setBytes(long bytes){
    this.bytes = bytes;
}


public void setUsername(String username){
    this.username = username;
}


public void setEndtime(Date endtime){
    this.endtime = endtime;
}


@Transient
public boolean isRound(){
    return round;
}


public Date getStarttime(){
    return starttime;
}


public void setDetailmsg(String detailmsg){
    this.detailmsg = detailmsg;
}


public void setRound(boolean round){
    this.round = round;
}


public void setType(String type){
    this.type = type;
}


public void setStatus(String status){
    this.status = status;
}


@Transient
public int getFilter(){
    return filter;
}


public String getDataid(){
    return dataid;
}


public String getOrgi(){
    return orgi;
}


public String toString(){
    return new StringBuffer().append("已处理：").append(this.getAtompages().intValue()).append(", 错误：").append(this.getErrors()).append("，处理速度：").append(this.getSpeed()).append("条/秒，线程数：").append(this.getThreads()).append(this.getDetailmsg() != null ? "，详细信息：" + this.getDetailmsg() : "").toString();
}


public long getPages(){
    return this.pages;
}


public Date getEndtime(){
    return endtime;
}


public int getThreads(){
    return threads;
}


public String getErrormsg(){
    return errormsg;
}


}