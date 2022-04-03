package org.sdrc.childinfo.domain;
 import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "uuid_generator")
public class UUIdGenerator implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "uuid_generator_id_pk")
 private  Integer uuidGeneratorId;

@Column(name = "month")
 private  int month;

@Column(name = "year")
 private  int year;

@Column(name = "uuid", length = 50)
 private  String uuid;

@Column(name = "created_date")
 private  Timestamp createdDate;

@Column(name = "isLive")
 private  boolean isLive;


public boolean getIsLive(){
    return isLive;
}


public void setMonth(int month){
    this.month = month;
}


public void setCreatedDate(Timestamp createdDate){
    this.createdDate = createdDate;
}


public int getYear(){
    return year;
}


public String getUuid(){
    return uuid;
}


public void setUuidGeneratorId(Integer uuidGeneratorId){
    this.uuidGeneratorId = uuidGeneratorId;
}


public void setIsLive(boolean isLive){
    this.isLive = isLive;
}


public Timestamp getCreatedDate(){
    return createdDate;
}


public int getMonth(){
    return month;
}


public Integer getUuidGeneratorId(){
    return uuidGeneratorId;
}


public void setUuid(String uuid){
    this.uuid = uuid;
}


public void setYear(int year){
    this.year = year;
}


}