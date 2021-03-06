package com.fosun.fc.projects.creepers.entity;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "T_CREEPERS_ENT_BASIC")
@NamedQuery(name = "TCreepersEntBasic.findAll", query = "SELECT t FROM TCreepersEntBasic t")
public class TCreepersEntBasic implements Serializable{

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_ENT_BASIC_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_ENT_BASIC")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_ENT_BASIC_ID_GENERATOR")
@Column(unique = true, nullable = false)
 private  long id;

@Column(length = 200)
 private  String addr;

@Column(name = "CREW_NUMBER", length = 60)
 private  String crewNumber;

@Column(length = 50)
 private  String email;

@Column(name = "IS_INVEST", length = 10)
 private  String isInvest;

@Column(name = "IS_TRANSFER", length = 10)
 private  String isTransfer;

@Column(name = "IS_WEBSITE", length = 10)
 private  String isWebsite;

@Column(name = "MER_NAME", length = 200)
 private  String merName;

@Column(name = "MER_NO", length = 100)
 private  String merNo;

@Column(length = 100)
 private  String phone;

@Column(name = "POST_CODE", length = 30)
 private  String postCode;

@Column(length = 50)
 private  String status;

public TCreepersEntBasic() {
}
public String getPhone(){
    return this.phone;
}


public void setIsTransfer(String isTransfer){
    this.isTransfer = isTransfer;
}


public void setMerNo(String merNo){
    this.merNo = merNo;
}


public String getIsWebsite(){
    return this.isWebsite;
}


public void setIsWebsite(String isWebsite){
    this.isWebsite = isWebsite;
}


public void setPostCode(String postCode){
    this.postCode = postCode;
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


public void setPhone(String phone){
    this.phone = phone;
}


public String getIsInvest(){
    return this.isInvest;
}


public String getStatus(){
    return this.status;
}


public void setStatus(String status){
    this.status = status;
}


public void setIsInvest(String isInvest){
    this.isInvest = isInvest;
}


public String getPostCode(){
    return this.postCode;
}


public void setCrewNumber(String crewNumber){
    this.crewNumber = crewNumber;
}


public void setAddr(String addr){
    this.addr = addr;
}


public void setEmail(String email){
    this.email = email;
}


public String getMerName(){
    return this.merName;
}


public void setId(long id){
    this.id = id;
}


public String getEmail(){
    return this.email;
}


public String getAddr(){
    return this.addr;
}


public String getCrewNumber(){
    return this.crewNumber;
}


public String getIsTransfer(){
    return this.isTransfer;
}


}