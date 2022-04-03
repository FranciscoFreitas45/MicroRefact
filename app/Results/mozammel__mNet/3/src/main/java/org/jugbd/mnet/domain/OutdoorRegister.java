package org.jugbd.mnet.domain;
 import org.hibernate.validator.constraints.NotEmpty;
import org.jugbd.mnet.domain.enums.Status;
import javax.persistence;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.jugbd.mnet.Request.PatientRequest;
import org.jugbd.mnet.Request.Impl.PatientRequestImpl;
import org.jugbd.mnet.DTO.Patient;
import org.jugbd.mnet.Request.VitalRequest;
import org.jugbd.mnet.Request.Impl.VitalRequestImpl;
import org.jugbd.mnet.DTO.Vital;
import org.jugbd.mnet.Request.VisitRequest;
import org.jugbd.mnet.Request.Impl.VisitRequestImpl;
import org.jugbd.mnet.DTO.Visit;
import org.jugbd.mnet.Request.ChiefComplaintRequest;
import org.jugbd.mnet.Request.Impl.ChiefComplaintRequestImpl;
import org.jugbd.mnet.DTO.ChiefComplaint;
import org.jugbd.mnet.Request.ExaminationRequest;
import org.jugbd.mnet.Request.Impl.ExaminationRequestImpl;
import org.jugbd.mnet.DTO.Examination;
import org.jugbd.mnet.Request.TreatmentPlanRequest;
import org.jugbd.mnet.Request.Impl.TreatmentPlanRequestImpl;
import org.jugbd.mnet.DTO.TreatmentPlan;
import org.jugbd.mnet.Request.PictureInformationRequest;
import org.jugbd.mnet.Request.Impl.PictureInformationRequestImpl;
import org.jugbd.mnet.DTO.PictureInformation;
@Entity
public class OutdoorRegister extends PersistentObject{

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@Version
 private  Long version;

@NotNull
@NotEmpty(message = "Register id can not be empty")
 private  String registrationId;

@Transient
 private  Patient patient;

@NotNull(message = "Registration Date can not be empty")
@Temporal(TemporalType.TIMESTAMP)
 private  Date startDatetime;

@Temporal(TemporalType.TIMESTAMP)
 private  Date stopDatetime;

@Transient
 private  Set<Vital> vitals;

@Transient
 private  Set<Visit> visits;

@Column(length = 6)
@Enumerated(EnumType.STRING)
 private  Status status;

@Transient
 private  ChiefComplaint chiefComplaint;

@Transient
 private  Examination examination;

@OneToOne
@JoinColumn(name = "diagnosis_id")
 private  Diagnosis diagnosis;

@Transient
 private  TreatmentPlan treatmentPlan;

@Transient
 private  PictureInformation pictureInformation;

@Valid
@Embedded
 private  PatientContact patientContact;

@Column(columnDefinition = "LONGTEXT")
 private  String outcome;

@Column(columnDefinition = "LONGTEXT")
 private  String remarks;

@Lob
 private  String followUpAdvice;

@Column(name = "id")
 private Long id;

@Transient
 private PatientRequest patientrequest = new PatientRequestImpl();;

@Transient
 private VitalRequest vitalrequest = new VitalRequestImpl();;

@Transient
 private VisitRequest visitrequest = new VisitRequestImpl();;

@Column(name = "id")
 private Long id;

@Transient
 private ChiefComplaintRequest chiefcomplaintrequest = new ChiefComplaintRequestImpl();;

@Column(name = "id")
 private Long id;

@Transient
 private ExaminationRequest examinationrequest = new ExaminationRequestImpl();;

@Column(name = "id")
 private Long id;

@Transient
 private TreatmentPlanRequest treatmentplanrequest = new TreatmentPlanRequestImpl();;

@Column(name = "id")
 private Long id;

@Transient
 private PictureInformationRequest pictureinformationrequest = new PictureInformationRequestImpl();;


public String getOutcome(){
    return outcome;
}


public String getFollowUpAdvice(){
    return followUpAdvice;
}


public Examination getExamination(){
  this.examination = examinationrequest.getExamination(this.id);
return this.examination;
}


public OutdoorRegister setStartDatetime(Date startDatetime){
    this.startDatetime = startDatetime;
    return this;
}


public Long getId(){
    return id;
}


public OutdoorRegister setTreatmentPlan(TreatmentPlan treatmentPlan){
  this.treatmentPlan = treatmentplanrequest.setTreatmentPlan(treatmentPlan,this.id);
return this.treatmentPlan;
}


public OutdoorRegister setRegistrationId(String registrationId){
    this.registrationId = registrationId;
    return this;
}


public Status getStatus(){
    return status;
}


public OutdoorRegister setExamination(Examination examination){
  this.examination = examinationrequest.setExamination(examination,this.id);
return this.examination;
}


public Set<Visit> getVisits(){
  this.visits = visitrequest.getVisits(this.id);
return this.visits;
}


public OutdoorRegister setVitals(Set<Vital> vitals){
  this.vitals = vitalrequest.setVitals(vitals,this.id);
return this.vitals;
}


public OutdoorRegister setPatient(Patient patient){
  this.patient = patientrequest.setPatient(patient,this.id);
return this.patient;
}


public OutdoorRegister setChiefComplaint(ChiefComplaint chiefComplaint){
  this.chiefComplaint = chiefcomplaintrequest.setChiefComplaint(chiefComplaint,this.id);
return this.chiefComplaint;
}


public TreatmentPlan getTreatmentPlan(){
  this.treatmentPlan = treatmentplanrequest.getTreatmentPlan(this.id);
return this.treatmentPlan;
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
  this.chiefComplaint = chiefcomplaintrequest.getChiefComplaint(this.id);
return this.chiefComplaint;
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
  this.vitals = vitalrequest.getVitals(this.id);
return this.vitals;
}


public String getRegistrationId(){
    return registrationId;
}


public Patient getPatient(){
  this.patient = patientrequest.getPatient(this.id);
return this.patient;
}


public OutdoorRegister setStatus(Status status){
    this.status = status;
    return this;
}


public OutdoorRegister setVisits(Set<Visit> visits){
  this.visits = visitrequest.setVisits(visits,this.id);
return this.visits;
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
  this.pictureInformation = pictureinformationrequest.getPictureInformation(this.id);
return this.pictureInformation;
}


public OutdoorRegister setPictureInformation(PictureInformation pictureInformation){
  this.pictureInformation = pictureinformationrequest.setPictureInformation(pictureInformation,this.id);
return this.pictureInformation;
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