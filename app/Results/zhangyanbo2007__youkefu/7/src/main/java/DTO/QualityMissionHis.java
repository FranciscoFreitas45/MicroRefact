package DTO;
 import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://9";


public String getQualitydisorgan(){
    return qualitydisorgan;
}


public String getQualitytype(){
    return qualitytype;
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


public Date getUpdatetime(){
    return updatetime;
}


public int getQualityscore(){
    return qualityscore;
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


public String getRejectremarks(){
    return rejectremarks;
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


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
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


public String getFilterid(){
    return filterid;
}


public int getQualitypass(){
    return qualitypass;
}


public String getQualityorgan(){
    return qualityorgan;
}


public String getAgentdata(){
    return agentdata;
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


public String getOrgi(){
    return orgi;
}


public void setQualitydisorgan(String qualitydisorgan){
    this.qualitydisorgan = qualitydisorgan;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setQualitydisorgan"))

.queryParam("qualitydisorgan",qualitydisorgan)
;
restTemplate.put(builder.toUriString(),null);
}


public void setQualitydisuser(String qualitydisuser){
    this.qualitydisuser = qualitydisuser;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setQualitydisuser"))

.queryParam("qualitydisuser",qualitydisuser)
;
restTemplate.put(builder.toUriString(),null);
}


public void setDataid(String dataid){
    this.dataid = dataid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDataid"))

.queryParam("dataid",dataid)
;
restTemplate.put(builder.toUriString(),null);
}


public void setQualitytype(String qualitytype){
    this.qualitytype = qualitytype;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setQualitytype"))

.queryParam("qualitytype",qualitytype)
;
restTemplate.put(builder.toUriString(),null);
}


public void setFormfilterid(String formfilterid){
    this.formfilterid = formfilterid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setFormfilterid"))

.queryParam("formfilterid",formfilterid)
;
restTemplate.put(builder.toUriString(),null);
}


public void setQualitytime(Date qualitytime){
    this.qualitytime = qualitytime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setQualitytime"))

.queryParam("qualitytime",qualitytime)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAssuser(String assuser){
    this.assuser = assuser;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAssuser"))

.queryParam("assuser",assuser)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTemplateid(String templateid){
    this.templateid = templateid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTemplateid"))

.queryParam("templateid",templateid)
;
restTemplate.put(builder.toUriString(),null);
}


public void setQualitystatus(String qualitystatus){
    this.qualitystatus = qualitystatus;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setQualitystatus"))

.queryParam("qualitystatus",qualitystatus)
;
restTemplate.put(builder.toUriString(),null);
}


public void setOrgi(String orgi){
    this.orgi = orgi;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOrgi"))

.queryParam("orgi",orgi)
;
restTemplate.put(builder.toUriString(),null);
}


public void setActid(String actid){
    this.actid = actid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setActid"))

.queryParam("actid",actid)
;
restTemplate.put(builder.toUriString(),null);
}


public void setOrgan(String organ){
    this.organ = organ;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOrgan"))

.queryParam("organ",organ)
;
restTemplate.put(builder.toUriString(),null);
}


public void setFilterid(String filterid){
    this.filterid = filterid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setFilterid"))

.queryParam("filterid",filterid)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTaskid(String taskid){
    this.taskid = taskid;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTaskid"))

.queryParam("taskid",taskid)
;
restTemplate.put(builder.toUriString(),null);
}


}