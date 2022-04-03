package org.jugbd.mnet.DTO;
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.jugbd.mnet.domain.enums.Status;
import javax.persistence;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
public class Vital extends PersistentObjectimplements Auditable{

 private  Long id;

 private  Long version;

 private  Double height;

 private  Double weight;

 private  Double bmi;

 private  Double temperature;

 private  Integer pulse;

 private  Integer respiratoryRate;

 private  Integer systolic;

 private  Integer diastolic;

 private  Double bloodOxygenSaturation;

 private  Patient patient;

 private  Register register;

 private  OutdoorRegister outdoorRegister;

 private  Status status;


public Double getBmi(){
    return bmi;
}


public Integer getRespiratoryRate(){
    return respiratoryRate;
}


public Double getWeight(){
    return weight;
}


@Override
public Long getId(){
    return id;
}


public Integer getDiastolic(){
    return diastolic;
}


public Status getStatus(){
    return status;
}


public Double getHeight(){
    return height;
}


public Integer getSystolic(){
    return systolic;
}


public Integer getPulse(){
    return pulse;
}


public OutdoorRegister getOutdoorRegister(){
    return outdoorRegister;
}


public Long getVersion(){
    return version;
}


public Double getBloodOxygenSaturation(){
    return bloodOxygenSaturation;
}


public Patient getPatient(){
    return patient;
}


public Register getRegister(){
    return register;
}


public Double getTemperature(){
    return temperature;
}


}