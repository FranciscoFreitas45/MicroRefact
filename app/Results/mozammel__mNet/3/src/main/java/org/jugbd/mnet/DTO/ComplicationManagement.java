package org.jugbd.mnet.DTO;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;
import org.jugbd.mnet.domain.enums.Outcome;
import javax.persistence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
public class ComplicationManagement extends PersistentObjectimplements Auditable{

 private  Long id;

 private  Long version;

 private  String postOperativeComplication;

 private  String managementOfComplication;

 private  Outcome outcome;

 private  String comment;

 private  Integer hospitalStays;

 private  String caseSummery;

 private  Register register;

 private  Long registerId;


public Long getVersion(){
    return version;
}


public Integer getHospitalStays(){
    return hospitalStays;
}


public String getManagementOfComplication(){
    return managementOfComplication;
}


public Outcome getOutcome(){
    return outcome;
}


@Override
public Long getId(){
    return id;
}


public String getCaseSummery(){
    return caseSummery;
}


public Register getRegister(){
    return register;
}


public String getPostOperativeComplication(){
    return postOperativeComplication;
}


public String getComment(){
    return comment;
}


public Long getRegisterId(){
    return registerId;
}


}