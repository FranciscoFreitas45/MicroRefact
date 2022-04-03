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
@Table(name = "te_position")
public class TePosition {

 private  long serialVersionUID;

 private  long poId;

 private  Date timestamp;

 private  long poDepartment;

 private  String poName;

 private  String poDescription;

 private  String creator;

 private  Date createTime;

// Constructors
/**
 * default constructor
 */
public TePosition() {
}/**
 * full constructor
 */
public TePosition(long poDepartment, String poName, String poDescription, String creator, Date createTime) {
    this.poDepartment = poDepartment;
    this.poName = poName;
    this.poDescription = poDescription;
    this.creator = creator;
    this.createTime = createTime;
}
public void setPoName(String poName){
    this.poName = poName;
}


@Column(name = "createTime", length = 19)
public Date getCreateTime(){
    return this.createTime;
}


public void setCreator(String creator){
    this.creator = creator;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


@Column(name = "creator", length = 64)
public String getCreator(){
    return this.creator;
}


public void setPoDepartment(long poDepartment){
    this.poDepartment = poDepartment;
}


public void setPoId(long poId){
    this.poId = poId;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "poId", unique = true, nullable = false)
public long getPoId(){
    return this.poId;
}


@Column(name = "poDepartment")
public long getPoDepartment(){
    return this.poDepartment;
}


public void setPoDescription(String poDescription){
    this.poDescription = poDescription;
}


@Version
@Column(name = "timestamp", nullable = false, length = 19)
public Date getTimestamp(){
    return this.timestamp;
}


@Column(name = "poName", length = 128)
public String getPoName(){
    return this.poName;
}


@Override
public String toString(){
    return "TePosition [poId:" + poId + ", timestamp:" + timestamp + ", poDepartment:" + poDepartment + ", poName:" + poName + ", poDescription:" + poDescription + ", creator:" + creator + ", createTime:" + createTime + "]";
}


public void setTimestamp(Date timestamp){
    this.timestamp = timestamp;
}


@Column(name = "poDescription", length = 1024)
public String getPoDescription(){
    return this.poDescription;
}


}