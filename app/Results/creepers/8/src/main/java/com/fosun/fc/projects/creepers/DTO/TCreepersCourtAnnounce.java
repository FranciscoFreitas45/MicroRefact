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
public class TCreepersCourtAnnounce {

 private  long serialVersionUID;

 private  long id;

 private  String announceContent;

 private  Date announceDt;

 private  String announceType;

 private  String courtName;

 private  String memo;

 private  String merName;

 private  String merNo;

public TCreepersCourtAnnounce() {
}
public void setMerNo(String merNo){
    this.merNo = merNo;
}


public String getAnnounceType(){
    return this.announceType;
}


public String getMerNo(){
    return merNo;
}


public long getId(){
    return this.id;
}


public String getAnnounceContent(){
    return this.announceContent;
}


public String getCourtName(){
    return this.courtName;
}


public String getMemo(){
    return this.memo;
}


public String getMerName(){
    return merName;
}


public void setId(long id){
    this.id = id;
}


public Date getAnnounceDt(){
    return this.announceDt;
}


}