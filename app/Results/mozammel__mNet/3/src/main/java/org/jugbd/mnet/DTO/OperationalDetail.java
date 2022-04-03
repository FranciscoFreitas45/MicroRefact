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


public String getDonorSite(){
    return donorSite;
}


@Override
public Long getId(){
    return id;
}


public YesNo getDrain(){
    return drain;
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


public Register getRegister(){
    return register;
}


public String getRecipientSite(){
    return recipientSite;
}


public String getNameOfAnaesthetist(){
    return nameOfAnaesthetist;
}


public String getNameOfOperation(){
    return nameOfOperation;
}


public String getIndication(){
    return indication;
}


public String getPlasty(){
    return plasty;
}


}