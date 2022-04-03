package com.zis.requirement.bean;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
@Entity
@Table(name = "departmentinfo")
public class Departmentinfo {

@Id
@GeneratedValue
@Column(name = "dId")
 private  Integer did;

@Column(name = "partName", length = 30, nullable = false)
 private  String partName;

@Column(name = "institute", length = 30, nullable = false)
 private  String institute;

@Column(name = "college", length = 30, nullable = false)
 private  String college;

@Column(name = "years", nullable = false)
 private  Integer years;

@Column(name = "GMT_CREATE", updatable = false)
@Temporal(TemporalType.TIMESTAMP)
 private  Date gmtCreate;

@Column(name = "GMT_MODIFY")
@Temporal(TemporalType.TIMESTAMP)
 private  Date gmtModify;

@Version
@Column(name = "version")
 private  Integer version;

// Constructors
/**
 * default constructor
 */
public Departmentinfo() {
}/**
 * minimal constructor
 */
public Departmentinfo(String partName, String institute, String college, Integer years) {
    this.partName = partName;
    this.institute = institute;
    this.college = college;
    this.years = years;
}/**
 * full constructor
 */
public Departmentinfo(String partName, String institute, String college, Integer years, Date gmtCreate, Date gmtModify, Integer version) {
    this.partName = partName;
    this.institute = institute;
    this.college = college;
    this.years = years;
    this.gmtCreate = gmtCreate;
    this.gmtModify = gmtModify;
    this.version = version;
}
public void setCollege(String college){
    this.college = college;
}


public Integer getVersion(){
    return this.version;
}


public Integer getDid(){
    return this.did;
}


public void setPartName(String partName){
    this.partName = partName;
}


public String getInstitute(){
    return this.institute;
}


public void setInstitute(String institute){
    this.institute = institute;
}


public String getCollege(){
    return this.college;
}


public void setVersion(Integer version){
    this.version = version;
}


public Date getGmtCreate(){
    return gmtCreate;
}


public Date getGmtModify(){
    return gmtModify;
}


public void setGmtModify(Date gmtModify){
    this.gmtModify = gmtModify;
}


public void setGmtCreate(Date gmtCreate){
    this.gmtCreate = gmtCreate;
}


public void setYears(Integer years){
    this.years = years;
}


public Integer getYears(){
    return years;
}


public String getPartName(){
    return this.partName;
}


public void setDid(Integer did){
    this.did = did;
}


}