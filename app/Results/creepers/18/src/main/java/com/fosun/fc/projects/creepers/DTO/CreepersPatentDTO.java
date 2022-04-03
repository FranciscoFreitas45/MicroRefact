package com.fosun.fc.projects.creepers.DTO;
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

 private  Date applyDt;

 private  String categoryNo;


public String getPatentName(){
    return patentName;
}


public void setApplyDt(Date applyDt){
    this.applyDt = applyDt;
}


public Date getApplyDt(){
    return applyDt;
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


public String getApplicant(){
    return applicant;
}


public String getCategoryNo(){
    return categoryNo;
}


}