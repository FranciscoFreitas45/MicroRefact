package com.fosun.fc.projects.creepers.DTO;
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
public class TCreepersPatent {

 private  long serialVersionUID;

 private  long id;

 private  String applicant;

 private  Date applyDt;

 private  String categoryNo;

 private  String merName;

 private  String merNo;

 private  String patentName;

 private  String patentNo;

 private  String patentType;

public TCreepersPatent() {
}
public String getPatentName(){
    return this.patentName;
}


public String getMerNo(){
    return merNo;
}


public long getId(){
    return this.id;
}


public void setPatentNo(String patentNo){
    this.patentNo = patentNo;
}


public Date getApplyDt(){
    return this.applyDt;
}


public void setApplicant(String applicant){
    this.applicant = applicant;
}


public String getPatentNo(){
    return this.patentNo;
}


public String getPatentType(){
    return this.patentType;
}


public String getApplicant(){
    return this.applicant;
}


public String getMerName(){
    return this.merName;
}


public void setId(long id){
    this.id = id;
}


public String getCategoryNo(){
    return this.categoryNo;
}


}