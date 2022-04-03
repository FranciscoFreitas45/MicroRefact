package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_sms_record")
@org.hibernate.annotations.Proxy(lazy = false)
public class SmsResult {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String code;

 private  String userid;

 private  String groupid;

 private  String description;

 private  String templettitle;

 private  String smstext;

 private  Date subtime;

 private  Date sendtime;

 private  String smsid;

 private  String extention;

 private  String extno;

 private  String sendresult;

 private  String organ;

 private  String smstype;

 private  String appkey;

 private  boolean sendok;

 private  String dataid;

 private  String taskid;

 private  String filterid;

 private  String actid;

 private  String batid;

 private  String phonenumber;

 private  String distype;

 private  String templettype;

 private  Date createtime;

 private  String orgi;

 private  String iconstr;

 private  String memo;

 private  String typeid;

 private  int layoutcols;

 private  String datatype;

 private  String charttype;


public String getName(){
    return name;
}


public void setFilterid(String filterid){
    this.filterid = filterid;
}


public boolean isSendok(){
    return sendok;
}


public String getExtention(){
    return extention;
}


public Date getSendtime(){
    return sendtime;
}


public String getExtno(){
    return extno;
}


public String getBatid(){
    return batid;
}


public void setBatid(String batid){
    this.batid = batid;
}


public void setAppkey(String appkey){
    this.appkey = appkey;
}


@Transient
public String getTitle(){
    return this.groupid;
}


public void setExtno(String extno){
    this.extno = extno;
}


public String getSmstext(){
    return smstext;
}


public String getSmstype(){
    return smstype;
}


public void setId(String id){
    this.id = id;
}


public String getIconstr(){
    return iconstr;
}


public String getCode(){
    return code;
}


public void setDatatype(String datatype){
    this.datatype = datatype;
}


public void setGroupid(String groupid){
    this.groupid = groupid;
}


public void setSubtime(Date subtime){
    this.subtime = subtime;
}


public void setCode(String code){
    this.code = code;
}


public String getSendresult(){
    return sendresult;
}


public void setTaskid(String taskid){
    this.taskid = taskid;
}


public void setSendok(boolean sendok){
    this.sendok = sendok;
}


public Date getSubtime(){
    return subtime;
}


public void setSendtime(Date sendtime){
    this.sendtime = sendtime;
}


public String getTypeid(){
    return typeid;
}


public void setTemplettype(String templettype){
    this.templettype = templettype;
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


public int getLayoutcols(){
    return layoutcols;
}


public void setDataid(String dataid){
    this.dataid = dataid;
}


public void setIconstr(String iconstr){
    this.iconstr = iconstr;
}


public void setName(String name){
    this.name = name;
}


public String getTaskid(){
    return taskid;
}


public void setSmsid(String smsid){
    this.smsid = smsid;
}


public void setCharttype(String charttype){
    this.charttype = charttype;
}


public void setSendresult(String sendresult){
    this.sendresult = sendresult;
}


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


public void setDescription(String description){
    this.description = description;
}


public void setTypeid(String typeid){
    this.typeid = typeid;
}


public String getDescription(){
    return description;
}


public void setPhonenumber(String phonenumber){
    this.phonenumber = phonenumber;
}


public String getDatatype(){
    return datatype;
}


public Date getCreatetime(){
    return createtime;
}


public String getActid(){
    return actid;
}


public void setLayoutcols(int layoutcols){
    this.layoutcols = layoutcols;
}


public String getFilterid(){
    return filterid;
}


public String getPhonenumber(){
    return phonenumber;
}


public void setActid(String actid){
    this.actid = actid;
}


public String getSmsid(){
    return smsid;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getTemplettitle(){
    return templettitle;
}


public String getUserid(){
    return userid;
}


public String getCharttype(){
    return charttype;
}


public void setUserid(String userid){
    this.userid = userid;
}


public String getTemplettype(){
    return templettype;
}


public void setExtention(String extention){
    this.extention = extention;
}


public void setDistype(String distype){
    this.distype = distype;
}


public String getDistype(){
    return distype;
}


public void setSmstype(String smstype){
    this.smstype = smstype;
}


public String getAppkey(){
    return appkey;
}


public String getGroupid(){
    return groupid;
}


public void setMemo(String memo){
    this.memo = memo;
}


public void setSmstext(String smstext){
    this.smstext = smstext;
}


public String getDataid(){
    return dataid;
}


public void setTemplettitle(String templettitle){
    this.templettitle = templettitle;
}


public String getOrgi(){
    return orgi;
}


}