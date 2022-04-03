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

 private Long id;


public String getOutcome(){
    return outcome;
}


public String getFollowUpAdvice(){
    return followUpAdvice;
}


public Examination getExamination(){
    return examination;
}


public OutdoorRegister setStartDatetime(Date startDatetime){
    this.startDatetime = startDatetime;
    return this;
}


public Long getId(){
    return id;
}


public OutdoorRegister setTreatmentPlan(TreatmentPlan treatmentPlan){
    this.treatmentPlan = treatmentPlan;
    return this;
}


public OutdoorRegister setRegistrationId(String registrationId){
    this.registrationId = registrationId;
    return this;
}


public Status getStatus(){
    return status;
}


public OutdoorRegister setExamination(Examination examination){
    this.examination = examination;
    return this;
}


public Set<Visit> getVisits(){
    return visits;
}


public OutdoorRegister setVitals(Set<Vital> vitals){
    this.vitals = vitals;
    return this;
}


public OutdoorRegister setPatient(Patient patient){
    this.patient = patient;
    return this;
}


public OutdoorRegister setChiefComplaint(ChiefComplaint chiefComplaint){
    this.chiefComplaint = chiefComplaint;
    return this;
}


public TreatmentPlan getTreatmentPlan(){
    return treatmentPlan;
}


public OutdoorRegister setId(Long id){
    this.id = id;
    return this;
}


public Date getStartDatetime(){
    return startDatetime;
}


public OutdoorRegister setOutcome(String outcome){
    this.outcome = outcome;
    return this;
}


public ChiefComplaint getChiefComplaint(){
    return chiefComplaint;
}


public Long getVersion(){
    return version;
}


public OutdoorRegister setVersion(Long version){
    this.version = version;
    return this;
}


public String getRemarks(){
    return remarks;
}


public OutdoorRegister setStopDatetime(Date stopDatetime){
    this.stopDatetime = stopDatetime;
    return this;
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


public OutdoorRegister setStatus(Status status){
    this.status = status;
    return this;
}


public OutdoorRegister setVisits(Set<Visit> visits){
    this.visits = visits;
    return this;
}


public OutdoorRegister setFollowUpAdvice(String followUpAdvice){
    this.followUpAdvice = followUpAdvice;
    return this;
}


public OutdoorRegister setDiagnosis(Diagnosis diagnosis){
    this.diagnosis = diagnosis;
    return this;
}


public PictureInformation getPictureInformation(){
    return pictureInformation;
}


public OutdoorRegister setPictureInformation(PictureInformation pictureInformation){
    this.pictureInformation = pictureInformation;
    return this;
}


public PatientContact getPatientContact(){
    return patientContact;
}


@Override
public String toString(){
    final StringBuffer sb = new StringBuffer("OutdoorRegister{");
    sb.append("registrationId='").append(registrationId).append('\'');
    sb.append(", id=").append(id);
    sb.append(", patient=").append(patient);
    sb.append('}');
    return sb.toString();
}


public OutdoorRegister setPatientContact(PatientContact patientContact){
    this.patientContact = patientContact;
    return this;
}


public Diagnosis getDiagnosis(){
    return diagnosis;
}


public OutdoorRegister setRemarks(String remarks){
    this.remarks = remarks;
    return this;
}


public Date getStopDatetime(){
    return stopDatetime;
}


}