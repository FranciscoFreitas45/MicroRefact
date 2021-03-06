package com.fosun.fc.projects.creepers.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "T_CREEPERS_ENT_INTEL")
@NamedQuery(name = "TCreepersEntIntel.findAll", query = "SELECT t FROM TCreepersEntIntel t")
public class TCreepersEntIntel {

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_ENT_INTEL_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_ENT_INTEL")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_ENT_INTEL_ID_GENERATOR")
@Column(unique = true, nullable = false)
 private  long id;

@Column(length = 200)
 private  String detail;

@Column(name = "MER_NO", length = 100)
 private  String merNo;

@Column(length = 200)
 private  String name;

@Column(name = "PLEDGEE_NAME", length = 50)
 private  String pledgeeName;

@Column(name = "PLEDGEE_PERIOD", length = 50)
 private  String pledgeePeriod;

@Column(name = "QUALITY_NAME", length = 50)
 private  String qualityName;

@Column(name = "SEQ_NO", length = 5)
 private  String seqNo;

@Column(length = 50)
 private  String status;

@Column(name = "\"TYPE\"", length = 20)
 private  String type;

public TCreepersEntIntel() {
}
public void setName(String name){
    this.name = name;
}


public String getQualityName(){
    return this.qualityName;
}


public void setMerNo(String merNo){
    this.merNo = merNo;
}


public String getName(){
    return this.name;
}


public String getPledgeeName(){
    return this.pledgeeName;
}


public void setDetail(String detail){
    this.detail = detail;
}


public String getDetail(){
    return this.detail;
}


public String getMerNo(){
    return this.merNo;
}


public void setPledgeePeriod(String pledgeePeriod){
    this.pledgeePeriod = pledgeePeriod;
}


public long getId(){
    return this.id;
}


public String getSeqNo(){
    return this.seqNo;
}


public String getStatus(){
    return this.status;
}


public void setType(String type){
    this.type = type;
}


public void setStatus(String status){
    this.status = status;
}


public void setSeqNo(String seqNo){
    this.seqNo = seqNo;
}


public void setQualityName(String qualityName){
    this.qualityName = qualityName;
}


public String getType(){
    return this.type;
}


public void setId(long id){
    this.id = id;
}


public String getPledgeePeriod(){
    return this.pledgeePeriod;
}


public void setPledgeeName(String pledgeeName){
    this.pledgeeName = pledgeeName;
}


}