package com.fosun.fc.projects.creepers.dto;
 import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
public class CreepersCourtAnnounceDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String merNo;

 private  String merName;

 private  String announceType;

 private  String courtName;

 private  String announceContent;

@Temporal(TemporalType.DATE)
 private  Date announceDt;

public CreepersCourtAnnounceDTO() {
}
public void setMerNo(String merNo){
    this.merNo = merNo;
}


public void setAnnounceDt(Date announceDt){
    this.announceDt = announceDt;
}


public void setAnnounceContent(String announceContent){
    this.announceContent = announceContent;
}


public String getMerName(){
    return merName;
}


public String getAnnounceType(){
    return announceType;
}


public void setCourtName(String courtName){
    this.courtName = courtName;
}


public String getMerNo(){
    return merNo;
}


public void setMerName(String merName){
    this.merName = merName;
}


public Date getAnnounceDt(){
    return announceDt;
}


public String getAnnounceContent(){
    return announceContent;
}


public void setAnnounceType(String announceType){
    this.announceType = announceType;
}


public String getCourtName(){
    return courtName;
}


}