package com.fosun.fc.projects.creepers.dto;
 import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
public class CreepersPatentDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String merName;

 private  String patentType;

 private  String patentNo;

 private  String patentName;

 private  String applicant;

@Temporal(TemporalType.DATE)
 private  Date applyDt;

 private  String categoryNo;


public String getPatentName(){
    return patentName;
}


public void setMerName(String merName){
    this.merName = merName;
}


public void setApplyDt(Date applyDt){
    this.applyDt = applyDt;
}


public void setPatentNo(String patentNo){
    this.patentNo = patentNo;
}


public Date getApplyDt(){
    return applyDt;
}


public void setCategoryNo(String categoryNo){
    this.categoryNo = categoryNo;
}


public void setApplicant(String applicant){
    this.applicant = applicant;
}


public String getPatentNo(){
    return patentNo;
}


public String getPatentType(){
    return patentType;
}


public String getMerName(){
    return merName;
}


public void setPatentType(String patentType){
    this.patentType = patentType;
}


public String getApplicant(){
    return applicant;
}


public void setPatentName(String patentName){
    this.patentName = patentName;
}


public String getCategoryNo(){
    return categoryNo;
}


}