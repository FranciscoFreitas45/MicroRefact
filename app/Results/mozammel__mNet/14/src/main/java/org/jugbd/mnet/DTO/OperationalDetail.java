package org.jugbd.mnet.DTO;
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotEmpty;
import org.jugbd.mnet.domain.enums.RequiredNotRequired;
import org.jugbd.mnet.domain.enums.YesNo;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import org.jugbd.mnet.Request.RegisterRequest;
import org.jugbd.mnet.Request.Impl.RegisterRequestImpl;
import org.jugbd.mnet.DTO.Register;
public class OperationalDetail extends PersistentObjectimplements Auditable{

 private  Long id;

 private  Date dateTime;

 private  String anaesthesia;

 private  String nameOfOperation;

 private  String indication;

 private  String nameOfSurgeon;

 private  String nameOfAnaesthetist;

 private  String findings;

 private  String incision;

 private  String donorSite;

 private  String plasty;

 private  String recipientSite;

 private  RequiredNotRequired bloodTransfusion;

 private  YesNo drain;

 private  String comment;

 private  Register register;

 private Long id;

 private RegisterRequest registerrequest = new RegisterRequestImpl();;


public void setAnaesthesia(String anaesthesia){
    this.anaesthesia = anaesthesia;
}


public String getDonorSite(){
    return donorSite;
}


@Override
public Long getId(){
    return id;
}


public void setIncision(String incision){
    this.incision = incision;
}


public void setRegister(Register register){
 registerrequest.setRegister(register,this.id);
}



public void setNameOfOperation(String nameOfOperation){
    this.nameOfOperation = nameOfOperation;
}


public void setIndication(String indication){
    this.indication = indication;
}


public void setDrain(YesNo drain){
    this.drain = drain;
}


public YesNo getDrain(){
    return drain;
}


public void setId(Long id){
    this.id = id;
}


public String getNameOfSurgeon(){
    return nameOfSurgeon;
}


public String getComment(){
    return comment;
}


public String getAnaesthesia(){
    return anaesthesia;
}


public Date getDateTime(){
    return dateTime;
}


public String getIncision(){
    return incision;
}


public RequiredNotRequired getBloodTransfusion(){
    return bloodTransfusion;
}


public String getFindings(){
    return findings;
}


public void setRecipientSite(String recipientSite){
    this.recipientSite = recipientSite;
}


public void setNameOfSurgeon(String nameOfSurgeon){
    this.nameOfSurgeon = nameOfSurgeon;
}


public void setFindings(String findings){
    this.findings = findings;
}


public void setDonorSite(String donorSite){
    this.donorSite = donorSite;
}


public Register getRegister(){
  this.register = registerrequest.getRegister(this.id);
return this.register;
}


public void setDateTime(Date dateTime){
    this.dateTime = dateTime;
}


public String getRecipientSite(){
    return recipientSite;
}


public String getNameOfAnaesthetist(){
    return nameOfAnaesthetist;
}


public void setNameOfAnaesthetist(String nameOfAnaesthetist){
    this.nameOfAnaesthetist = nameOfAnaesthetist;
}


public String getNameOfOperation(){
    return nameOfOperation;
}


public void setComment(String comment){
    this.comment = comment;
}


public String getIndication(){
    return indication;
}


public String getPlasty(){
    return plasty;
}


public void setPlasty(String plasty){
    this.plasty = plasty;
}


public void setBloodTransfusion(RequiredNotRequired bloodTransfusion){
    this.bloodTransfusion = bloodTransfusion;
}


}