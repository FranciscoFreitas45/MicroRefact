package org.jugbd.mnet.DTO;
 import org.hibernate.validator.constraints.NotEmpty;
import org.jugbd.mnet.domain.enums.Status;
import javax.persistence;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
public class OutdoorRegister extends PersistentObject{

 private  Long id;

 private  Long version;

 private  String registrationId;

 private  Patient patient;

 private  Date startDatetime;

 private  Date stopDatetime;

 private  Set<Vital> vitals;

 private  Set<Visit> visits;

 private  Status status;

 private  ChiefComplaint chiefComplaint;

 private  Examination examination;

 private  Diagnosis diagnosis;

 private  TreatmentPlan treatmentPlan;

 private  PictureInformation pictureInformation;

 private  PatientContact patientContact;

 private  String outcome;

 private  String remarks;

 private  String followUpAdvice;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";


public String getOutcome(){
    return outcome;
}


public String getFollowUpAdvice(){
    return followUpAdvice;
}


public Examination getExamination(){
    return examination;
}


public Long getId(){
    return id;
}


public Status getStatus(){
    return status;
}


public Set<Visit> getVisits(){
    return visits;
}


public TreatmentPlan getTreatmentPlan(){
    return treatmentPlan;
}


public Date getStartDatetime(){
    return startDatetime;
}


public ChiefComplaint getChiefComplaint(){
    return chiefComplaint;
}


public Long getVersion(){
    return version;
}


public String getRemarks(){
    return remarks;
}


public Set<Vital> getVitals(){
    return vitals;
}


public String getRegistrationId(){
    return registrationId;
}


public Patient getPatient(){
    return patient;
}


public PictureInformation getPictureInformation(){
    return pictureInformation;
}


public PatientContact getPatientContact(){
    return patientContact;
}


public Diagnosis getDiagnosis(){
    return diagnosis;
}


public Date getStopDatetime(){
    return stopDatetime;
}


public OutdoorRegister setExamination(Examination examination){
  this.examination = examinationrequest.setExamination(examination,this.id);
return this.examination;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setExamination"))

.queryParam("examination",examination)
;
OutdoorRegister aux = restTemplate.getForObject(builder.toUriString(),OutdoorRegister.class);
return aux;
}


}