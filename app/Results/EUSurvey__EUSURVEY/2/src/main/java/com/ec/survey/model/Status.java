package com.ec.survey.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import com.ec.survey.tools.ConversionTools;
@Entity
@Table(name = "STATUS")
public class Status {

 private  Integer id;

 private  int dbversion;

 private  Date updateDate;

 private  Date lastLDAPSynchronizationDate;

 private  Date lastLDAPSynchronization2Date;

 private  Date lastAnswerSetAnonymDate;


public void setUpdateDate(Date updateDate){
    this.updateDate = updateDate;
}


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


public void setLastAnswerSetAnonymDate(Date lastAnswerSetAnonymDate){
    this.lastAnswerSetAnonymDate = lastAnswerSetAnonymDate;
}


public void setId(Integer id){
    this.id = id;
}


public void setLastLDAPSynchronization2Date(Date lastLDAPSynchronization2Date){
    this.lastLDAPSynchronization2Date = lastLDAPSynchronization2Date;
}


@Id
@Column(name = "STATUS_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


public void setLastLDAPSynchronizationDate(Date lastLDAPSynchronizationDate){
    this.lastLDAPSynchronizationDate = lastLDAPSynchronizationDate;
}


public void setDbversion(int dbversion){
    this.dbversion = dbversion;
}


@Column(name = "LDAPSYNC2DATE")
@DateTimeFormat(pattern = ConversionTools.DateTimeFormat)
public Date getLastLDAPSynchronization2Date(){
    return lastLDAPSynchronization2Date;
}


}