package com.empl.mgr.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
public class TeTraining {

 private  long serialVersionUID;

 private  long id;

 private  Date timestamp;

 private  String name;

 private  String description;

 private  Integer number;

 private  String startTime;

 private  String endTime;

 private  boolean isInsertAttend;

 private  Integer state;

 private  Date createTime;

 private  String creator;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";

// Constructors
/**
 * default constructor
 */
public TeTraining() {
}/**
 * full constructor
 */
public TeTraining(String name, String description, Integer number, String startTime, String endTime, boolean isInsertAttend, Integer state, Date createTime, String creator) {
    this.name = name;
    this.description = description;
    this.number = number;
    this.startTime = startTime;
    this.endTime = endTime;
    this.isInsertAttend = isInsertAttend;
    this.state = state;
    this.createTime = createTime;
    this.creator = creator;
}
@Column(name = "isInsertAttend")
public boolean getIsInsertAttend(){
    return this.isInsertAttend;
}


@Column(name = "createTime", length = 19)
public Date getCreateTime(){
    return this.createTime;
}


@Column(name = "name", length = 128)
public String getName(){
    return this.name;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public long getId(){
    return this.id;
}


@Column(name = "description", length = 128)
public String getDescription(){
    return this.description;
}


@Column(name = "creator", length = 128)
public String getCreator(){
    return this.creator;
}


@Column(name = "number")
public Integer getNumber(){
    return this.number;
}


@Column(name = "endTime", length = 16)
public String getEndTime(){
    return this.endTime;
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


@Column(name = "startTime", length = 16)
public String getStartTime(){
    return this.startTime;
}


public void setNumber(Integer number){
    this.number = number;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setNumber"))

.queryParam("number",number)
;
restTemplate.put(builder.toUriString(),null);
}


}