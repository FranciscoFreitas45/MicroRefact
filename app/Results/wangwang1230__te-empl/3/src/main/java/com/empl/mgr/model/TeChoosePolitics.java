package com.empl.mgr.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
@Entity
@Table(name = "te_choose_politics")
public class TeChoosePolitics {

 private  long serialVersionUID;

 private  long polId;

 private  Date timestamp;

 private  String polName;

// Constructors
/**
 * default constructor
 */
public TeChoosePolitics() {
}/**
 * full constructor
 */
public TeChoosePolitics(String polName) {
    this.polName = polName;
}
@Column(name = "polName", length = 64)
public String getPolName(){
    return this.polName;
}


@Version
@Column(name = "timestamp", nullable = false, length = 19)
public Date getTimestamp(){
    return this.timestamp;
}


@Override
public String toString(){
    return "TeChoosePolitics [polId:" + polId + ", timestamp:" + timestamp + ", polName:" + polName + "]";
}


public void setPolId(long polId){
    this.polId = polId;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "polId", unique = true, nullable = false)
public long getPolId(){
    return this.polId;
}


public void setTimestamp(Date timestamp){
    this.timestamp = timestamp;
}


public void setPolName(String polName){
    this.polName = polName;
}


}