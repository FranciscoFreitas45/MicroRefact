package com.fosun.fc.projects.creepers.entity;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "T_CREEPERS_ENT_LICENSE")
@NamedQuery(name = "TCreepersEntLicense.findAll", query = "SELECT t FROM TCreepersEntLicense t")
public class TCreepersEntLicense {

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_ENT_LICENSE_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_ENT_LICENSE")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_ENT_LICENSE_ID_GENERATOR")
@Column(unique = true, nullable = false)
 private  long id;

@Column(length = 100)
 private  String authority;

@Column(length = 1000)
 private  String content;

@Column(length = 100)
 private  String detail;

@Temporal(TemporalType.DATE)
@Column(name = "EFF_DT")
 private  Date effDt;

@Temporal(TemporalType.DATE)
@Column(name = "EXP_DT")
 private  Date expDt;

@Column(name = "LICENSE_NAME", length = 200)
 private  String licenseName;

@Column(name = "LICENSE_NO", length = 100)
 private  String licenseNo;

@Column(name = "MER_NO", length = 100)
 private  String merNo;

@Column(name = "SEQ_NO", length = 5)
 private  String seqNo;

@Column(length = 100)
 private  String status;

public TCreepersEntLicense() {
}
public String getLicenseNo(){
    return this.licenseNo;
}


public void setLicenseNo(String licenseNo){
    this.licenseNo = licenseNo;
}


public void setContent(String content){
    this.content = content;
}


public void setMerNo(String merNo){
    this.merNo = merNo;
}


public Date getEffDt(){
    return this.effDt;
}


public void setDetail(String detail){
    this.detail = detail;
}


public String getDetail(){
    return this.detail;
}


public String getContent(){
    return this.content;
}


public String getMerNo(){
    return this.merNo;
}


public void setLicenseName(String licenseName){
    this.licenseName = licenseName;
}


public long getId(){
    return this.id;
}


public String getLicenseName(){
    return this.licenseName;
}


public String getSeqNo(){
    return this.seqNo;
}


public String getAuthority(){
    return this.authority;
}


public String getStatus(){
    return this.status;
}


public void setStatus(String status){
    this.status = status;
}


public void setSeqNo(String seqNo){
    this.seqNo = seqNo;
}


public Date getExpDt(){
    return this.expDt;
}


public void setAuthority(String authority){
    this.authority = authority;
}


public void setExpDt(Date expDt){
    this.expDt = expDt;
}


public void setId(long id){
    this.id = id;
}


public void setEffDt(Date effDt){
    this.effDt = effDt;
}


}