package com.empl.mgr.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
@Entity
@Table(name = "te_employees_basic", uniqueConstraints = @UniqueConstraint(columnNames = "emIdentity"))
public class TeEmployeesBasic {

 private  long serialVersionUID;

 private  long emId;

 private  Date timestamp;

 private  Integer emState;

 private  String emPhoto;

 private  String emFullName;

 private  boolean emSex;

 private  String emIdentity;

 private  String emBirthday;

 private  String emParticipateTime;

 private  String emPhone;

 private  String emSocialSecurity;

 private  long emDeparemtn;

 private  long emPosition;

 private  long emEducation;

 private  long emMarriage;

 private  long emPolitics;

 private  long emNational;

 private  long emCurrentAddress;

 private  long emCensusRegister;

 private  Date createTime;

 private  String creator;

// Constructors
/**
 * default constructor
 */
public TeEmployeesBasic() {
}/**
 * full constructor
 */
public TeEmployeesBasic(Integer emState, String emPhoto, String emFullName, boolean emSex, String emIdentity, String emBirthday, String emParticipateTime, String emPhone, String emSocialSecurity, long emDeparemtn, long emPosition, long emEducation, long emMarriage, long emPolitics, long emNational, long emCurrentAddress, long emCensusRegister, Date createTime, String creator) {
    this.emState = emState;
    this.emPhoto = emPhoto;
    this.emFullName = emFullName;
    this.emSex = emSex;
    this.emIdentity = emIdentity;
    this.emBirthday = emBirthday;
    this.emParticipateTime = emParticipateTime;
    this.emPhone = emPhone;
    this.emSocialSecurity = emSocialSecurity;
    this.emDeparemtn = emDeparemtn;
    this.emPosition = emPosition;
    this.emEducation = emEducation;
    this.emMarriage = emMarriage;
    this.emPolitics = emPolitics;
    this.emNational = emNational;
    this.emCurrentAddress = emCurrentAddress;
    this.emCensusRegister = emCensusRegister;
    this.createTime = createTime;
    this.creator = creator;
}
@Column(name = "createTime", length = 19)
public Date getCreateTime(){
    return this.createTime;
}


public void setEmPhoto(String emPhoto){
    this.emPhoto = emPhoto;
}


public void setEmEducation(long emEducation){
    this.emEducation = emEducation;
}


@Column(name = "emPolitics")
public long getEmPolitics(){
    return this.emPolitics;
}


public void setEmPhone(String emPhone){
    this.emPhone = emPhone;
}


public void setEmParticipateTime(String emParticipateTime){
    this.emParticipateTime = emParticipateTime;
}


@Column(name = "emPhone", length = 32)
public String getEmPhone(){
    return this.emPhone;
}


public void setEmIdentity(String emIdentity){
    this.emIdentity = emIdentity;
}


@Column(name = "emBirthday", length = 16)
public String getEmBirthday(){
    return this.emBirthday;
}


@Column(name = "emPhoto", length = 64)
public String getEmPhoto(){
    return this.emPhoto;
}


@Column(name = "emNational")
public long getEmNational(){
    return this.emNational;
}


@Column(name = "emCurrentAddress")
public long getEmCurrentAddress(){
    return this.emCurrentAddress;
}


@Version
@Column(name = "timestamp", nullable = false, length = 19)
public Date getTimestamp(){
    return this.timestamp;
}


public void setEmPolitics(long emPolitics){
    this.emPolitics = emPolitics;
}


@Column(name = "emEducation")
public long getEmEducation(){
    return this.emEducation;
}


public void setEmState(Integer emState){
    this.emState = emState;
}


@Column(name = "emIdentity", unique = true, length = 32)
public String getEmIdentity(){
    return this.emIdentity;
}


@Column(name = "emMarriage")
public long getEmMarriage(){
    return this.emMarriage;
}


public void setEmPosition(long emPosition){
    this.emPosition = emPosition;
}


public void setTimestamp(Date timestamp){
    this.timestamp = timestamp;
}


public void setEmBirthday(String emBirthday){
    this.emBirthday = emBirthday;
}


@Column(name = "emSocialSecurity", length = 32)
public String getEmSocialSecurity(){
    return this.emSocialSecurity;
}


@Column(name = "emCensusRegister")
public long getEmCensusRegister(){
    return this.emCensusRegister;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "emId", unique = true, nullable = false)
public long getEmId(){
    return this.emId;
}


public void setCreator(String creator){
    this.creator = creator;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setEmFullName(String emFullName){
    this.emFullName = emFullName;
}


public void setEmId(long emId){
    this.emId = emId;
}


public void setEmMarriage(long emMarriage){
    this.emMarriage = emMarriage;
}


public void setEmDeparemtn(long emDeparemtn){
    this.emDeparemtn = emDeparemtn;
}


@Column(name = "emPosition")
public long getEmPosition(){
    return this.emPosition;
}


public void setEmCurrentAddress(long emCurrentAddress){
    this.emCurrentAddress = emCurrentAddress;
}


@Column(name = "creator", length = 64)
public String getCreator(){
    return this.creator;
}


public void setEmSex(boolean emSex){
    this.emSex = emSex;
}


public void setEmSocialSecurity(String emSocialSecurity){
    this.emSocialSecurity = emSocialSecurity;
}


@Column(name = "emFullName", length = 64)
public String getEmFullName(){
    return this.emFullName;
}


public void setEmCensusRegister(long emCensusRegister){
    this.emCensusRegister = emCensusRegister;
}


@Column(name = "emDeparemtn")
public long getEmDeparemtn(){
    return this.emDeparemtn;
}


@Column(name = "emSex")
public boolean getEmSex(){
    return this.emSex;
}


@Column(name = "emParticipateTime", length = 12)
public String getEmParticipateTime(){
    return this.emParticipateTime;
}


@Column(name = "emState")
public Integer getEmState(){
    return this.emState;
}


@Override
public String toString(){
    return "TeEmployeesBasic [emId:" + emId + ", timestamp:" + timestamp + ", emState:" + emState + ", emPhoto:" + emPhoto + ", emFullName:" + emFullName + ", emSex:" + emSex + ", emIdentity:" + emIdentity + ", emBirthday:" + emBirthday + ", emParticipateTime:" + emParticipateTime + ", emPhone:" + emPhone + ", emSocialSecurity:" + emSocialSecurity + ", emDeparemtn:" + emDeparemtn + ", emPosition:" + emPosition + ", emEducation:" + emEducation + ", emMarriage:" + emMarriage + ", emPolitics:" + emPolitics + ", emNational:" + emNational + ", emCurrentAddress:" + emCurrentAddress + ", emCensusRegister:" + emCensusRegister + ", createTime:" + createTime + ", creator:" + creator + "]";
}


public void setEmNational(long emNational){
    this.emNational = emNational;
}


}