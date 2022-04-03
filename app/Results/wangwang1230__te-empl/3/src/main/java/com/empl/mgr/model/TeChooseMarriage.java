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
@Table(name = "te_choose_marriage")
public class TeChooseMarriage {

 private  long serialVersionUID;

 private  long marId;

 private  Date timestamp;

 private  String marName;

// Constructors
/**
 * default constructor
 */
public TeChooseMarriage() {
}/**
 * full constructor
 */
public TeChooseMarriage(String marName) {
    this.marName = marName;
}
public void setMarName(String marName){
    this.marName = marName;
}


@Version
@Column(name = "timestamp", nullable = false, length = 19)
public Date getTimestamp(){
    return this.timestamp;
}


@Column(name = "marName", length = 64)
public String getMarName(){
    return this.marName;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "marId", unique = true, nullable = false)
public long getMarId(){
    return this.marId;
}


@Override
public String toString(){
    return "TeChooseMarriage [marId:" + marId + ", timestamp:" + timestamp + ", marName:" + marName + "]";
}


public void setMarId(long marId){
    this.marId = marId;
}


public void setTimestamp(Date timestamp){
    this.timestamp = timestamp;
}


}