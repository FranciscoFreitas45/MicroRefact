package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.ukefu.util.event.UserEvent;
@Entity
@Table(name = "uk_log_request")
@org.hibernate.annotations.Proxy(lazy = false)
public class RequestLog implements UserEvent{

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String funtype;

 private  String fundesc;

 private  String username;

 private  String userid;

 private  String usermail;

 private  Date starttime;

 private  Date endtime;

 private  String url;

 private  String parameters;

 private  String reportname;

 private  String type;

 private  String detailtype;

 private  String orgi;

 private  String ip;

 private  String hostname;

 private  String statues;

 private  String error;

 private  String reportdic;

 private  String flowid;

 private  Date createdate;

 private  String classname;

 private  String methodname;

 private  String filename;

 private  String linenumber;

 private  String throwable;

 private  long querytime;

 private  String optext;

 private  String field6;

 private  String field7;

 private  String field8;

 private  String triggerwarning;

 private  String triggertime;


public String getTriggertime(){
    return triggertime;
}


public String getName(){
    return name;
}


public void setOptext(String optext){
    this.optext = optext;
}


public void setHostname(String hostname){
    this.hostname = hostname;
}


public void setStarttime(Date starttime){
    this.starttime = starttime;
}


public String getDetailtype(){
    return detailtype;
}


public String getLinenumber(){
    return linenumber;
}


public void setFundesc(String fundesc){
    this.fundesc = fundesc;
}


public void setFuntype(String funtype){
    this.funtype = funtype;
}


public void setThrowable(String throwable){
    this.throwable = throwable;
}


public long getQuerytime(){
    return querytime;
}


public void setId(String id){
    this.id = id;
}


public String getThrowable(){
    return throwable;
}


public void setDetailtype(String detailtype){
    this.detailtype = detailtype;
}


public void setCreatedate(Date createdate){
    this.createdate = createdate;
}


public String getClassname(){
    return classname;
}


public void setIp(String ip){
    this.ip = ip;
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


public String getError(){
    return error;
}


public String getUrl(){
    return url;
}


public String getType(){
    return type;
}


public void setError(String error){
    this.error = error;
}


public void setMethodname(String methodname){
    this.methodname = methodname;
}


public String getFundesc(){
    return fundesc;
}


public void setReportdic(String reportdic){
    this.reportdic = reportdic;
}


public void setUsermail(String usermail){
    this.usermail = usermail;
}


public void setName(String name){
    this.name = name;
}


public Date getCreatedate(){
    return createdate;
}


public String getIp(){
    return ip;
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


public String getReportdic(){
    return reportdic;
}


public void setFlowid(String flowid){
    this.flowid = flowid;
}


public void setReportname(String reportname){
    this.reportname = reportname;
}


public String getUsername(){
    return username;
}


public void setFilename(String filename){
    this.filename = filename;
}


public void setQuerytime(long querytime){
    this.querytime = querytime;
}


public void setParameters(String parameters){
    this.parameters = parameters;
}


public void setField8(String field8){
    this.field8 = field8;
}


public String getHostname(){
    return hostname;
}


public String getMethodname(){
    return methodname;
}


public void setLinenumber(String linenumber){
    this.linenumber = linenumber;
}


public String getUserid(){
    return userid;
}


public String getReportname(){
    return reportname;
}


public void setUserid(String userid){
    this.userid = userid;
}


public String getStatues(){
    return statues;
}


public void setUsername(String username){
    this.username = username;
}


public String getOptext(){
    return optext;
}


public void setEndtime(Date endtime){
    this.endtime = endtime;
}


public void setStatues(String statues){
    this.statues = statues;
}


public Date getStarttime(){
    return starttime;
}


public String getField8(){
    return field8;
}


public void setType(String type){
    this.type = type;
}


public String getField6(){
    return field6;
}


public String getField7(){
    return field7;
}


public void setTriggertime(String triggertime){
    this.triggertime = triggertime;
}


public String getTriggerwarning(){
    return triggerwarning;
}


public String getFilename(){
    return filename;
}


public String getFlowid(){
    return flowid;
}


public String getFuntype(){
    return funtype;
}


public String getParameters(){
    return parameters;
}


public String getOrgi(){
    return orgi;
}


public void setClassname(String classname){
    this.classname = classname;
}


public void setTriggerwarning(String triggerwarning){
    this.triggerwarning = triggerwarning;
}


public String getUsermail(){
    return usermail;
}


public Date getEndtime(){
    return endtime;
}


}