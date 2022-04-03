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
@Table(name = "te_choose_education")
public class TeChooseEducation {

 private  long serialVersionUID;

 private  long eduId;

 private  Date timestamp;

 private  String eduName;

// Constructors
/**
 * default constructor
 */
public TeChooseEducation() {
}/**
 * full constructor
 */
public TeChooseEducation(String eduName) {
    this.eduName = eduName;
}
public void setEduName(String eduName){
    this.eduName = eduName;
}


public void setEduId(long eduId){
    this.eduId = eduId;
}


@Version
@Column(name = "timestamp", nullable = false, length = 19)
public Date getTimestamp(){
    return this.timestamp;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "eduId", unique = true, nullable = false)
public long getEduId(){
    return this.eduId;
}


@Column(name = "eduName", length = 64)
public String getEduName(){
    return this.eduName;
}


@Override
public String toString(){
    return "TeChooseEducation [eduId:" + eduId + ", timestamp:" + timestamp + ", eduName:" + eduName + "]";
}


public void setTimestamp(Date timestamp){
    this.timestamp = timestamp;
}


}