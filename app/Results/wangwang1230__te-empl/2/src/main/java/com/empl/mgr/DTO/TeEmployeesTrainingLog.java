package com.empl.mgr.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
public class TeEmployeesTrainingLog {

 private  long serialVersionUID;

 private  long id;

 private  Date timestamp;

 private  Integer state;

 private  long emplId;

 private  long trainingItemId;

 private  String applyTime;

 private  String note;

 private  Date createTime;

 private  String creator;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";

// Constructors
/**
 * default constructor
 */
public TeEmployeesTrainingLog() {
}/**
 * full constructor
 */
public TeEmployeesTrainingLog(Integer state, long emplId, long trainingItemId, String applyTime, String note, Date createTime, String creator) {
    this.state = state;
    this.emplId = emplId;
    this.trainingItemId = trainingItemId;
    this.applyTime = applyTime;
    this.note = note;
    this.createTime = createTime;
    this.creator = creator;
}
@Column(name = "createTime", length = 19)
public Date getCreateTime(){
    return this.createTime;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public long getId(){
    return this.id;
}


@Column(name = "trainingItemId")
public long getTrainingItemId(){
    return this.trainingItemId;
}


@Column(name = "note", length = 1024)
public String getNote(){
    return this.note;
}


@Column(name = "creator", length = 128)
public String getCreator(){
    return this.creator;
}


@Column(name = "emplId")
public long getEmplId(){
    return this.emplId;
}


@Column(name = "applyTime", length = 12)
public String getApplyTime(){
    return this.applyTime;
}


@Column(name = "state")
public Integer getState(){
    return this.state;
}


@Version
@Column(name = "timestamp", nullable = false, length = 19)
public Date getTimestamp(){
    return this.timestamp;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCreateTime"))

.queryParam("createTime",createTime)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCreator(String creator){
    this.creator = creator;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCreator"))

.queryParam("creator",creator)
;
restTemplate.put(builder.toUriString(),null);
}


public void setEmplId(long emplId){
    this.emplId = emplId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setEmplId"))

.queryParam("emplId",emplId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTrainingItemId(long trainingItemId){
    this.trainingItemId = trainingItemId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTrainingItemId"))

.queryParam("trainingItemId",trainingItemId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setApplyTime(String applyTime){
    this.applyTime = applyTime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setApplyTime"))

.queryParam("applyTime",applyTime)
;
restTemplate.put(builder.toUriString(),null);
}


public void setNote(String note){
    this.note = note;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setNote"))

.queryParam("note",note)
;
restTemplate.put(builder.toUriString(),null);
}


public void setState(Integer state){
    this.state = state;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setState"))

.queryParam("state",state)
;
restTemplate.put(builder.toUriString(),null);
}


}