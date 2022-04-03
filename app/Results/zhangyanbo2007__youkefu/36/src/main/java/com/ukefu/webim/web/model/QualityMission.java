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
@Table(name = "uk_qc_mission")
@Proxy(lazy = true)
public class QualityMission implements Serializable{

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  Date createtime;

 private  String creater;

 private  Date updatetime;

 private  String orgi;

 private  String assuser;

 private  Date asstime;

 private  String status;

 private  String filtertype;

 private  String dataid;

 private  String datakey;

 private  String datavalue;

 private  String templateid;

 private  String actid;

 private  String formfilterid;

 private  String filterid;

 private  String taskid;

 private  int datastatus;

 private  String qualitystatus;

 private  String qualitydisorgan;

 private  String qualitydisuser;

 private  String qualityorgan;

 private  String qualityuser;

 private  int qualityscore;

 private  Date qualitytime;

 private  String qualitytype;

 private  String agentdata;

 private  String organ;


public String getName(){
    return name;
}


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


public void setDatastatus(int datastatus){
    this.datastatus = datastatus;
}


public String getStatus(){
    return status;
}


public Date getQualitytime(){
    return qualitytime;
}


public void setDatavalue(String datavalue){
    this.datavalue = datavalue;
}


public String getQualitydisuser(){
    return qualitydisuser;
}


public String getQualityuser(){
    return qualityuser;
}


public String getFiltertype(){
    return filtertype;
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


public String getQualitystatus(){
    return qualitystatus;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setDataid(String dataid){
    this.dataid = dataid;
}


public void setName(String name){
    this.name = name;
}


public String getTaskid(){
    return taskid;
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


public Date getAsstime(){
    return asstime;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public void setAsstime(Date asstime){
    this.asstime = asstime;
}


public String getDatavalue(){
    return datavalue;
}


public void setFormfilterid(String formfilterid){
    this.formfilterid = formfilterid;
}


public Date getCreatetime(){
    return createtime;
}


public String getActid(){
    return actid;
}


public void setFiltertype(String filtertype){
    this.filtertype = filtertype;
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


public String getCreater(){
    return creater;
}


public int getDatastatus(){
    return datastatus;
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


public String getDatakey(){
    return datakey;
}


public void setStatus(String status){
    this.status = status;
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


public void setQualitystatus(String qualitystatus){
    this.qualitystatus = qualitystatus;
}


public void setDatakey(String datakey){
    this.datakey = datakey;
}


public String getOrgi(){
    return orgi;
}


public void setQualityscore(int qualityscore){
    this.qualityscore = qualityscore;
}


}