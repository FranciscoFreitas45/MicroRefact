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
@Table(name = "T_CREEPERS_MER_BASIC")
@NamedQuery(name = "TCreepersMerBasic.findAll", query = "SELECT t FROM TCreepersMerBasic t")
public class TCreepersMerBasic {

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_MER_BASIC_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_MER_BASIC")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_MER_BASIC_ID_GENERATOR")
@Column(unique = true, nullable = false)
 private  long id;

@Column(length = 300)
 private  String addr;

@Temporal(TemporalType.DATE)
@Column(name = "APPROVAL_DATE")
 private  Date approvalDate;

@Temporal(TemporalType.DATE)
@Column(name = "FOUND_DT")
 private  Date foundDt;

@Column(name = "LEGAL_REP", length = 50)
 private  String legalRep;

@Column(name = "MER_NAME", length = 200)
 private  String merName;

@Column(name = "MER_NO", length = 100)
 private  String merNo;

@Column(name = "MER_TYPE", length = 20)
 private  String merType;

@Temporal(TemporalType.DATE)
@Column(name = "OPR_END_DT")
 private  Date oprEndDt;

@Temporal(TemporalType.DATE)
@Column(name = "OPR_START_DT")
 private  Date oprStartDt;

@Column(name = "REG_AUTHORITY", length = 200)
 private  String regAuthority;

@Column(name = "REG_CAPITAL", length = 100)
 private  String regCapital;

@Column(name = "REG_STATUS", length = 100)
 private  String regStatus;

@Column(name = "SCOPE", length = 2000)
 private  String scope;

@Column(name = "SHAREHOLDER_TYPE", length = 100)
 private  String shareholderType;

public TCreepersMerBasic() {
}
public void setRegStatus(String regStatus){
    this.regStatus = regStatus;
}


public Date getApprovalDate(){
    return this.approvalDate;
}


public String getMerNo(){
    return this.merNo;
}


public void setMerName(String merName){
    this.merName = merName;
}


public long getId(){
    return this.id;
}


public void setLegalRep(String legalRep){
    this.legalRep = legalRep;
}


public void setRegAuthority(String regAuthority){
    this.regAuthority = regAuthority;
}


public String getMerName(){
    return this.merName;
}


public void setId(long id){
    this.id = id;
}


public String getAddr(){
    return this.addr;
}


public String getRegCapital(){
    return this.regCapital;
}


public void setApprovalDate(Date approvalDate){
    this.approvalDate = approvalDate;
}


public String getLegalRep(){
    return this.legalRep;
}


public void setMerNo(String merNo){
    this.merNo = merNo;
}


public String getMerType(){
    return this.merType;
}


public String getRegStatus(){
    return this.regStatus;
}


public void setRegCapital(String regCapital){
    this.regCapital = regCapital;
}


public Date getOprStartDt(){
    return this.oprStartDt;
}


public Date getFoundDt(){
    return this.foundDt;
}


public void setOprEndDt(Date oprEndDt){
    this.oprEndDt = oprEndDt;
}


public Date getOprEndDt(){
    return this.oprEndDt;
}


public String getShareholderType(){
    return this.shareholderType;
}


public void setAddr(String addr){
    this.addr = addr;
}


public void setOprStartDt(Date oprStartDt){
    this.oprStartDt = oprStartDt;
}


public void setShareholderType(String shareholderType){
    this.shareholderType = shareholderType;
}


public void setFoundDt(Date foundDt){
    this.foundDt = foundDt;
}


public void setScope(String scope){
    this.scope = scope;
}


public String getRegAuthority(){
    return this.regAuthority;
}


public String getScope(){
    return this.scope;
}


public void setMerType(String merType){
    this.merType = merType;
}


}