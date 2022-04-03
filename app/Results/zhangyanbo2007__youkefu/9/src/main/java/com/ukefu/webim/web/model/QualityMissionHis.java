package com.ukefu.webim.web.model;
 import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
@Entity
@Table(name = "uk_qc_mission_his")
@Proxy(lazy = false)
public class QualityMissionHis implements Serializable{

 private  long serialVersionUID;

 private  String id;

 private  Date createtime;

 private  Date updatetime;

 private  String organ;

 private  String orgi;

 private  String dataid;

 private  String templateid;

 private  String actid;

 private  String formfilterid;

 private  String filterid;

 private  String taskid;

 private  boolean datastatus;

 private  String qualitystatus;

 private  String qualitydisorgan;

 private  String qualitydisuser;

 private  String qualityorgan;

 private  String qualityuser;

 private  int qualityscore;

 private  Date qualitytime;

 private  String qualitytype;

 private  String agentdata;

 private  String assuser;

 private  String appealremarks;

 private  String arbremarks;

 private  String rejectremarks;

 private  String resultid;

 private  int qualitypass;

 private  int qualityappeal;

 private  int qualityarbitrate;


public void setFilterid(String filterid){
    this.filterid = filterid;
}


public String getQualitydisorgan(){
    return qualitydisorgan;
}


public String getQualitytype(){
    return qualitytype;
}


public void setTemplateid(String templateid){
    this.templateid = templateid;
}


public void setAgentdata(String agentdata){
    this.agentdata = agentdata;
}


public void setDatastatus(boolean datastatus){
    this.datastatus = datastatus;
}


public String getAppealremarks(){
    return appealremarks;
}


public Date getQualitytime(){
    return qualitytime;
}


public String getQualitydisuser(){
    return qualitydisuser;
}


public String getQualityuser(){
    return qualityuser;
}


public void setId(String id){
    this.id = id;
}


public void setQualitytype(String qualitytype){
    this.qualitytype = qualitytype;
}


public void setQualitydisuser(String qualitydisuser){
    this.qualitydisuser = qualitydisuser;
}


public void setQualitypass(int qualitypass){
    this.qualitypass = qualitypass;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setTaskid(String taskid){
    this.taskid = taskid;
}


public int getQualityscore(){
    return qualityscore;
}


public void setQualityorgan(String qualityorgan){
    this.qualityorgan = qualityorgan;
}


public void setOrgan(String organ){
    this.organ = organ;
}


public String getOrgan(){
    return organ;
}


public String getArbremarks(){
    return arbremarks;
}


public String getQualitystatus(){
    return qualitystatus;
}


public String getResultid(){
    return resultid;
}


public void setDataid(String dataid){
    this.dataid = dataid;
}


public String getRejectremarks(){
    return rejectremarks;
}


public void setQualityappeal(int qualityappeal){
    this.qualityappeal = qualityappeal;
}


public String getTaskid(){
    return taskid;
}


public int getQualityappeal(){
    return qualityappeal;
}


public String getAssuser(){
    return assuser;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


public void setAssuser(String assuser){
    this.assuser = assuser;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public void setFormfilterid(String formfilterid){
    this.formfilterid = formfilterid;
}


public int getQualityarbitrate(){
    return qualityarbitrate;
}


public Date getCreatetime(){
    return createtime;
}


public String getActid(){
    return actid;
}


public void setAppealremarks(String appealremarks){
    this.appealremarks = appealremarks;
}


public String getFilterid(){
    return filterid;
}


public void setActid(String actid){
    this.actid = actid;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public int getQualitypass(){
    return qualitypass;
}


public void setQualityarbitrate(int qualityarbitrate){
    this.qualityarbitrate = qualityarbitrate;
}


public void setQualityuser(String qualityuser){
    this.qualityuser = qualityuser;
}


public void setQualitydisorgan(String qualitydisorgan){
    this.qualitydisorgan = qualitydisorgan;
}


public void setQualitytime(Date qualitytime){
    this.qualitytime = qualitytime;
}


public String getQualityorgan(){
    return qualityorgan;
}


public String getAgentdata(){
    return agentdata;
}


public void setRejectremarks(String rejectremarks){
    this.rejectremarks = rejectremarks;
}


public String getTemplateid(){
    return templateid;
}


public String getFormfilterid(){
    return formfilterid;
}


public String getDataid(){
    return dataid;
}


public void setResultid(String resultid){
    this.resultid = resultid;
}


public void setQualitystatus(String qualitystatus){
    this.qualitystatus = qualitystatus;
}


public void setArbremarks(String arbremarks){
    this.arbremarks = arbremarks;
}


public String getOrgi(){
    return orgi;
}


public void setQualityscore(int qualityscore){
    this.qualityscore = qualityscore;
}


public boolean isDatastatus(){
    return datastatus;
}


}