package com.empl.mgr.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";

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


@Column(name = "emPolitics")
public long getEmPolitics(){
    return this.emPolitics;
}


@Column(name = "emPhone", length = 32)
public String getEmPhone(){
    return this.emPhone;
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


@Column(name = "emEducation")
public long getEmEducation(){
    return this.emEducation;
}


@Column(name = "emIdentity", unique = true, length = 32)
public String getEmIdentity(){
    return this.emIdentity;
}


@Column(name = "emMarriage")
public long getEmMarriage(){
    return this.emMarriage;
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


@Column(name = "emPosition")
public long getEmPosition(){
    return this.emPosition;
}


@Column(name = "creator", length = 64)
public String getCreator(){
    return this.creator;
}


@Column(name = "emFullName", length = 64)
public String getEmFullName(){
    return this.emFullName;
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


public void setEmDeparemtn(long emDeparemtn){
    this.emDeparemtn = emDeparemtn;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setEmDeparemtn"))

.queryParam("emDeparemtn",emDeparemtn)
;
restTemplate.put(builder.toUriString(),null);
}


public void setEmPolitics(long emPolitics){
    this.emPolitics = emPolitics;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setEmPolitics"))

.queryParam("emPolitics",emPolitics)
;
restTemplate.put(builder.toUriString(),null);
}


}