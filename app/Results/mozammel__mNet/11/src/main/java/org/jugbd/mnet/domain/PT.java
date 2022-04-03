package org.jugbd.mnet.domain;
 import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import java.util.Date;
@Embeddable
public class PT {

@Temporal(TemporalType.DATE)
@DateTimeFormat(pattern = "dd/MM/yyyy")
 private  Date dateOfInvestigation;

@Size(max = 100)
 private  String patient;

@Size(max = 100)
 private  String control;

@Size(max = 100)
 private  String inIndex;

@Size(max = 100)
 private  String comment;


public void setPatient(String patient){
    this.patient = patient;
}


public void setInIndex(String inIndex){
    this.inIndex = inIndex;
}


public String getControl(){
    return control;
}


public void setDateOfInvestigation(Date dateOfInvestigation){
    this.dateOfInvestigation = dateOfInvestigation;
}


public void setComment(String comment){
    this.comment = comment;
}


public String getComment(){
    return comment;
}


public String getPatient(){
    return patient;
}


public void setControl(String control){
    this.control = control;
}


public Date getDateOfInvestigation(){
    return dateOfInvestigation;
}


public String getInIndex(){
    return inIndex;
}


}