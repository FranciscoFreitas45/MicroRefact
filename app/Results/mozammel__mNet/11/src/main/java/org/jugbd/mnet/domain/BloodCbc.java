package org.jugbd.mnet.domain;
 import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
@Embeddable
public class BloodCbc implements Serializable{

@Temporal(TemporalType.DATE)
@DateTimeFormat(pattern = "dd/MM/yyyy")
 private  Date dateOfInvestigation;

@Size(max = 100)
 private  String tc;

@Size(max = 100)
 private  String dc;

@Size(max = 100)
 private  String hb;

@Size(max = 100)
 private  String esr;

@Size(max = 100)
 private  String pcv;

@Size(max = 1000)
 private  String comment;


public void setEsr(String esr){
    this.esr = esr;
}


public void setPcv(String pcv){
    this.pcv = pcv;
}


public void setDc(String dc){
    this.dc = dc;
}


public String getPcv(){
    return pcv;
}


public void setDateOfInvestigation(Date dateOfInvestigation){
    this.dateOfInvestigation = dateOfInvestigation;
}


public String getEsr(){
    return esr;
}


public String getHb(){
    return hb;
}


public String getDc(){
    return dc;
}


public void setTc(String tc){
    this.tc = tc;
}


public void setHb(String hb){
    this.hb = hb;
}


public void setComment(String comment){
    this.comment = comment;
}


public String getComment(){
    return comment;
}


public String getTc(){
    return tc;
}


public Date getDateOfInvestigation(){
    return dateOfInvestigation;
}


}