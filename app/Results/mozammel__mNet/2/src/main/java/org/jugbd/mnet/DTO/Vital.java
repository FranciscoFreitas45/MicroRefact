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


public void setBloodOxygenSaturation(Double bloodOxygenSaturation){
    this.bloodOxygenSaturation = bloodOxygenSaturation;
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


public Vital setRegister(Register register){
    this.register = register;
    return this;
}


public Status getStatus(){
    return status;
}


public Vital setOutdoorRegister(OutdoorRegister outdoorRegister){
    this.outdoorRegister = outdoorRegister;
    return this;
}


public Vital setPatient(Patient patient){
    this.patient = patient;
    return this;
}


public Double getHeight(){
    return height;
}


public Integer getSystolic(){
    return systolic;
}


public void setId(Long id){
    this.id = id;
}


public void setTemperature(Double temperature){
    this.temperature = temperature;
}


public void setPulse(Integer pulse){
    this.pulse = pulse;
}


public Integer getPulse(){
    return pulse;
}


public OutdoorRegister getOutdoorRegister(){
    return outdoorRegister;
}


public void setSystolic(Integer systolic){
    this.systolic = systolic;
}


public Long getVersion(){
    return version;
}


public Double getBloodOxygenSaturation(){
    return bloodOxygenSaturation;
}


public void setVersion(Long version){
    this.version = version;
}


public void setWeight(Double weight){
    this.weight = weight;
}


public void setHeight(Double height){
    this.height = height;
}


public void setRespiratoryRate(Integer respiratoryRate){
    this.respiratoryRate = respiratoryRate;
}


public Patient getPatient(){
    return patient;
}


public void setStatus(Status status){
    this.status = status;
}


public Register getRegister(){
    return register;
}


public void setDiastolic(Integer diastolic){
    this.diastolic = diastolic;
}


@Override
public String toString(){
    final StringBuffer sb = new StringBuffer("Vital{");
    sb.append("id=").append(id);
    sb.append(", version=").append(version);
    sb.append(", height=").append(height);
    sb.append(", weight=").append(weight);
    sb.append(", bmi=").append(bmi);
    sb.append(", temperature=").append(temperature);
    sb.append(", pulse=").append(pulse);
    sb.append(", respiratoryRate=").append(respiratoryRate);
    sb.append(", systolic=").append(systolic);
    sb.append(", diastolic=").append(diastolic);
    sb.append(", bloodOxygenSaturation=").append(bloodOxygenSaturation);
    sb.append(", patient=").append(patient);
    sb.append(", register=").append(register);
    sb.append(", outdoorRegister=").append(outdoorRegister);
    sb.append(", status=").append(status);
    sb.append('}');
    return sb.toString();
}


public Double getTemperature(){
    return temperature;
}


public void setBmi(Double bmi){
    this.bmi = bmi;
}


}