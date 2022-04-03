package com.ec.survey.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import com.ec.survey.tools.ConversionTools;
public class Status {

 private  Integer id;

 private  int dbversion;

 private  Date updateDate;

 private  Date lastLDAPSynchronizationDate;

 private  Date lastLDAPSynchronization2Date;

 private  Date lastAnswerSetAnonymDate;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


@Column(name = "ANSWERANONYMDATE")
@DateTimeFormat(pattern = ConversionTools.DateTimeFormat)
public Date getLastAnswerSetAnonymDate(){
    return lastAnswerSetAnonymDate;
}


@Column(name = "DBVERSION")
public int getDbversion(){
    return dbversion;
}


@Column(name = "LDAPSYNCDATE")
@DateTimeFormat(pattern = ConversionTools.DateTimeFormat)
public Date getLastLDAPSynchronizationDate(){
    return lastLDAPSynchronizationDate;
}


@Column(name = "DBUPDATE")
public Date getUpdateDate(){
    return updateDate;
}


@Id
@Column(name = "STATUS_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


@Column(name = "LDAPSYNC2DATE")
@DateTimeFormat(pattern = ConversionTools.DateTimeFormat)
public Date getLastLDAPSynchronization2Date(){
    return lastLDAPSynchronization2Date;
}


public void setDbversion(int dbversion){
    this.dbversion = dbversion;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDbversion"))

.queryParam("dbversion",dbversion)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUpdateDate(Date updateDate){
    this.updateDate = updateDate;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUpdateDate"))

.queryParam("updateDate",updateDate)
;
restTemplate.put(builder.toUriString(),null);
}


}