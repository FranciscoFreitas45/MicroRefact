package org.jugbd.mnet.web.controller;
 public class PatientSearchCmd {

 private  String healthId;

 private  String phoneNumber;

 private  String name;

 private  String registerId;

 private  String diagnosis;


public void setName(String name){
    this.name = name;
}


public void setRegisterId(String registerId){
    this.registerId = registerId;
}


public String getName(){
    return name;
}


public String getHealthId(){
    return healthId;
}


public void setPhoneNumber(String phoneNumber){
    this.phoneNumber = phoneNumber;
}


public void setDiagnosis(String diagnosis){
    this.diagnosis = diagnosis;
}


public void setHealthId(String healthId){
    this.healthId = healthId;
}


public String getPhoneNumber(){
    return phoneNumber;
}


@Override
public String toString(){
    return "PatientSearchCmd{" + "healthId='" + healthId + '\'' + ", phoneNumber='" + phoneNumber + '\'' + ", name='" + name + '\'' + '}';
}


public String getDiagnosis(){
    return diagnosis;
}


public String getRegisterId(){
    return registerId;
}


}