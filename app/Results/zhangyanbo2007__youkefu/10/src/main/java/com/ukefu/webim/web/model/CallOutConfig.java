package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_act_config")
@org.hibernate.annotations.Proxy(lazy = false)
public class CallOutConfig {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String orgi;

 private  String creater;

 private  String type;

 private  String dataid;

 private  Date createtime;

 private  Date updatetime;

 private  String username;

 private  boolean enablecallout;

 private  int countdown;

 private  boolean enabletagentthreads;

 private  int agentthreads;

 private  boolean enabletaithreads;

 private  int aithreads;

 private  boolean enablefthreads;

 private  int fthreads;

 private  boolean enableauto;

 private  boolean forecast;

 private  int forecastratio;

 private  int fmaxavgtime;

 private  int fminavgtime;

 private  int favgaftertime;

 private  String defaultvalue;

 private  String strategy;

 private  boolean previewautocallout;

 private  boolean appointment;


public String getStrategy(){
    return strategy;
}


public boolean isPreviewautocallout(){
    return previewautocallout;
}


public String getName(){
    return name;
}


public int getAgentthreads(){
    return agentthreads;
}


public void setEnabletaithreads(boolean enabletaithreads){
    this.enabletaithreads = enabletaithreads;
}


public void setAppointment(boolean appointment){
    this.appointment = appointment;
}


public void setFthreads(int fthreads){
    this.fthreads = fthreads;
}


public void setId(String id){
    this.id = id;
}


public void setForecast(boolean forecast){
    this.forecast = forecast;
}


public boolean isEnablefthreads(){
    return enablefthreads;
}


public boolean isAppointment(){
    return appointment;
}


public void setEnablefthreads(boolean enablefthreads){
    this.enablefthreads = enablefthreads;
}


public void setPreviewautocallout(boolean previewautocallout){
    this.previewautocallout = previewautocallout;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getDefaultvalue(){
    return defaultvalue;
}


public int getFthreads(){
    return fthreads;
}


public int getForecastratio(){
    return forecastratio;
}


public String getType(){
    return type;
}


public void setCountdown(int countdown){
    this.countdown = countdown;
}


public boolean isEnableauto(){
    return enableauto;
}


public boolean isEnablecallout(){
    return enablecallout;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setDataid(String dataid){
    this.dataid = dataid;
}


public int getFminavgtime(){
    return fminavgtime;
}


public void setFavgaftertime(int favgaftertime){
    this.favgaftertime = favgaftertime;
}


public int getAithreads(){
    return aithreads;
}


public void setAgentthreads(int agentthreads){
    this.agentthreads = agentthreads;
}


public void setName(String name){
    this.name = name;
}


public int getFmaxavgtime(){
    return fmaxavgtime;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
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


public void setFmaxavgtime(int fmaxavgtime){
    this.fmaxavgtime = fmaxavgtime;
}


public boolean isForecast(){
    return forecast;
}


public String getUsername(){
    return username;
}


public Date getCreatetime(){
    return createtime;
}


public void setStrategy(String strategy){
    this.strategy = strategy;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public void setFminavgtime(int fminavgtime){
    this.fminavgtime = fminavgtime;
}


public void setEnablecallout(boolean enablecallout){
    this.enablecallout = enablecallout;
}


public void setUsername(String username){
    this.username = username;
}


public boolean isEnabletagentthreads(){
    return enabletagentthreads;
}


public void setDefaultvalue(String defaultvalue){
    this.defaultvalue = defaultvalue;
}


public void setType(String type){
    this.type = type;
}


public void setEnabletagentthreads(boolean enabletagentthreads){
    this.enabletagentthreads = enabletagentthreads;
}


public int getFavgaftertime(){
    return favgaftertime;
}


public void setForecastratio(int forecastratio){
    this.forecastratio = forecastratio;
}


public String getDataid(){
    return dataid;
}


public void setAithreads(int aithreads){
    this.aithreads = aithreads;
}


public boolean isEnabletaithreads(){
    return enabletaithreads;
}


public void setEnableauto(boolean enableauto){
    this.enableauto = enableauto;
}


public String getOrgi(){
    return orgi;
}


public int getCountdown(){
    return countdown;
}


}