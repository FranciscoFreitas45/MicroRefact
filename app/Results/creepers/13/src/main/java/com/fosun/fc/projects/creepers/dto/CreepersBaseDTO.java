package com.fosun.fc.projects.creepers.dto;
 import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fosun.fc.modules.dto.BaseDTO;
public class CreepersBaseDTO extends BaseDTO{

 private  long serialVersionUID;

 public  String GT_createdDt;

 public  String LT_createdDt;

 public  String GT_updatedDt;

 public  String LT_updatedDt;

 public  String createdBy;

@Temporal(TemporalType.DATE)
 public  Date createdDt;

 public  String updatedBy;

@Temporal(TemporalType.DATE)
 public  Date updatedDt;


public void setLT_createdDt(String lT_createdDt){
    LT_createdDt = lT_createdDt;
}


public void setUpdatedBy(String updatedBy){
    this.updatedBy = updatedBy;
}


public String getUpdatedBy(){
    return updatedBy;
}


public void setGT_createdDt(String gT_createdDt){
    GT_createdDt = gT_createdDt;
}


public String getGT_updatedDt(){
    return GT_updatedDt;
}


public void setGT_updatedDt(String gT_updatedDt){
    GT_updatedDt = gT_updatedDt;
}


public Date getUpdatedDt(){
    return updatedDt;
}


public String getLT_updatedDt(){
    return LT_updatedDt;
}


public String getLT_createdDt(){
    return LT_createdDt;
}


public Date getCreatedDt(){
    return createdDt;
}


public void setCreatedBy(String createdBy){
    this.createdBy = createdBy;
}


public void setUpdatedDt(Date updatedDt){
    this.updatedDt = updatedDt;
}


public void setLT_updatedDt(String lT_updatedDt){
    LT_updatedDt = lT_updatedDt;
}


public void setCreatedDt(Date createdDt){
    this.createdDt = createdDt;
}


public String getCreatedBy(){
    return createdBy;
}


public String getGT_createdDt(){
    return GT_createdDt;
}


}