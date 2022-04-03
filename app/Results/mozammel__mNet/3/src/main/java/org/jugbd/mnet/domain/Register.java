package org.jugbd.mnet.domain;
 import org.hibernate.validator.constraints.NotEmpty;
import org.jugbd.mnet.domain.enums.Status;
import org.jugbd.mnet.domain.enums.Ward;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.jugbd.mnet.Request.PatientRequest;
import org.jugbd.mnet.Request.Impl.PatientRequestImpl;
import org.jugbd.mnet.DTO.Patient;
import org.jugbd.mnet.Request.VitalRequest;
import org.jugbd.mnet.Request.Impl.VitalRequestImpl;
import org.jugbd.mnet.DTO.Vital;
import org.jugbd.mnet.Request.OperationalDetailRequest;
import org.jugbd.mnet.Request.Impl.OperationalDetailRequestImpl;
import org.jugbd.mnet.DTO.OperationalDetail;
import org.jugbd.mnet.Request.VisitRequest;
import org.jugbd.mnet.Request.Impl.VisitRequestImpl;
import org.jugbd.mnet.DTO.Visit;
import org.jugbd.mnet.Request.MedicalHistoryRequest;
import org.jugbd.mnet.Request.Impl.MedicalHistoryRequestImpl;
import org.jugbd.mnet.DTO.MedicalHistory;
import org.jugbd.mnet.Request.ChiefComplaintRequest;
import org.jugbd.mnet.Request.Impl.ChiefComplaintRequestImpl;
import org.jugbd.mnet.DTO.ChiefComplaint;
import org.jugbd.mnet.Request.ExaminationRequest;
import org.jugbd.mnet.Request.Impl.ExaminationRequestImpl;
import org.jugbd.mnet.DTO.Examination;
import org.jugbd.mnet.Request.TreatmentPlanRequest;
import org.jugbd.mnet.Request.Impl.TreatmentPlanRequestImpl;
import org.jugbd.mnet.DTO.TreatmentPlan;
import org.jugbd.mnet.Request.ComplicationManagementRequest;
import org.jugbd.mnet.Request.Impl.ComplicationManagementRequestImpl;
import org.jugbd.mnet.DTO.ComplicationManagement;
import org.jugbd.mnet.Request.LifeStyleRequest;
import org.jugbd.mnet.Request.Impl.LifeStyleRequestImpl;
import org.jugbd.mnet.DTO.LifeStyle;
import org.jugbd.mnet.Request.InvestigationRequest;
import org.jugbd.mnet.Request.Impl.InvestigationRequestImpl;
import org.jugbd.mnet.DTO.Investigation;
import org.jugbd.mnet.Request.PictureInformationRequest;
import org.jugbd.mnet.Request.Impl.PictureInformationRequestImpl;
import org.jugbd.mnet.DTO.PictureInformation;
@Entity
public class Register extends PersistentObject{

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@Version
 private  Long version;

@NotNull
@NotEmpty(message = "Register id can not be empty")
 private  String registrationId;

@NotNull(message = "Admission date can not be empty")
@Temporal(TemporalType.DATE)
@DateTimeFormat(pattern = "dd/MM/yyyy")
 private  Date admissionDate;

@NotNull
@Column(length = 10)
@Enumerated(EnumType.STRING)
 private  Ward ward;

@Size(max = 100)
@Column(length = 100)
 private  String wardOther;

@NotEmpty
@Size(max = 32)
@Column(length = 32)
 private  String bedNumber;

@NotEmpty
@Size(max = 32)
@Column(length = 32)
 private  String unit;

@Transient
 private  Patient patient;

@Temporal(TemporalType.TIMESTAMP)
 private  Date startDatetime;

@Temporal(TemporalType.TIMESTAMP)
 private  Date stopDatetime;

@Transient
 private  Set<Vital> vitals;

@Transient
 private  Set<OperationalDetail> operationalDetails;

@Transient
 private  Set<Visit> visits;

@Column(length = 6)
@Enumerated(EnumType.STRING)
 private  Status status;

@Transient
 private  MedicalHistory medicalHistory;

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
 private  ComplicationManagement complicationManagement;

@Transient
 private  LifeStyle lifeStyle;

@Transient
 private  Set<Investigation> investigation;

@Transient
 private  PictureInformation pictureInformation;

@Valid
@Embedded
 private  PatientContact patientContact;

@Column(name = "outdoor_register_blnk", nullable = true)
 private  Long outdoorRegister;

@Column(name = "id")
 private Long id;

@Transient
 private PatientRequest patientrequest = new PatientRequestImpl();;

@Transient
 private VitalRequest vitalrequest = new VitalRequestImpl();;

@Transient
 private OperationalDetailRequest operationaldetailrequest = new OperationalDetailRequestImpl();;

@Transient
 private VisitRequest visitrequest = new VisitRequestImpl();;

@Column(name = "id")
 private Long id;

@Transient
 private MedicalHistoryRequest medicalhistoryrequest = new MedicalHistoryRequestImpl();;

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
 private ComplicationManagementRequest complicationmanagementrequest = new ComplicationManagementRequestImpl();;

@Column(name = "id")
 private Long id;

@Transient
 private LifeStyleRequest lifestylerequest = new LifeStyleRequestImpl();;

@Transient
 private InvestigationRequest investigationrequest = new InvestigationRequestImpl();;

@Column(name = "id")
 private Long id;

@Transient
 private PictureInformationRequest pictureinformationrequest = new PictureInformationRequestImpl();;


public void setStartDatetime(Date startDatetime){
    this.startDatetime = startDatetime;
}


public ComplicationManagement getComplicationManagement(){
  this.complicationManagement = complicationmanagementrequest.getComplicationManagement(this.id);
return this.complicationManagement;
}


public void setLifeStyle(LifeStyle lifeStyle){
 lifestylerequest.setLifeStyle(lifeStyle,this.id);
}



public void setRegistrationId(String registrationId){
    this.registrationId = registrationId;
}


public Status getStatus(){
    return status;
}


public Set<Visit> getVisits(){
  this.visits = visitrequest.getVisits(this.id);
return this.visits;
}


public MedicalHistory getMedicalHistory(){
  this.medicalHistory = medicalhistoryrequest.getMedicalHistory(this.id);
return this.medicalHistory;
}


public Set<Investigation> getInvestigation(){
  this.investigation = investigationrequest.getInvestigation(this.id);
return this.investigation;
}


public void setId(Long id){
    this.id = id;
}


public Date getStartDatetime(){
    return startDatetime;
}


public ChiefComplaint getChiefComplaint(){
  this.chiefComplaint = chiefcomplaintrequest.getChiefComplaint(this.id);
return this.chiefComplaint;
}


public void setBedNumber(String bedNumber){
    this.bedNumber = bedNumber;
}


public void setVersion(Long version){
    this.version = version;
}


public void setStopDatetime(Date stopDatetime){
    this.stopDatetime = stopDatetime;
}


public Set<OperationalDetail> getOperationalDetails(){
  this.operationalDetails = operationaldetailrequest.getOperationalDetails(this.id);
return this.operationalDetails;
}


public Set<Vital> getVitals(){
  this.vitals = vitalrequest.getVitals(this.id);
return this.vitals;
}


public void setWard(Ward ward){
    this.ward = ward;
}


public void setWardOther(String wardOther){
    this.wardOther = wardOther;
}


public void setVisits(Set<Visit> visits){
 visitrequest.setVisits(visits,this.id);
}



public void setDiagnosis(Diagnosis diagnosis){
    this.diagnosis = diagnosis;
}


public void setPictureInformation(PictureInformation pictureInformation){
 pictureinformationrequest.setPictureInformation(pictureInformation,this.id);
}



public PatientContact getPatientContact(){
    return patientContact;
}


public void setPatientContact(PatientContact patientContact){
    this.patientContact = patientContact;
}


public void setAdmissionDate(Date admissionDate){
    this.admissionDate = admissionDate;
}


public void setComplicationManagement(ComplicationManagement complicationManagement){
 complicationmanagementrequest.setComplicationManagement(complicationManagement,this.id);
}



public void setOperationalDetails(Set<OperationalDetail> operationalDetails){
 operationaldetailrequest.setOperationalDetails(operationalDetails,this.id);
}



public Examination getExamination(){
  this.examination = examinationrequest.getExamination(this.id);
return this.examination;
}


public Long getId(){
    return id;
}


public void setTreatmentPlan(TreatmentPlan treatmentPlan){
 treatmentplanrequest.setTreatmentPlan(treatmentPlan,this.id);
}



public void setExamination(Examination examination){
 examinationrequest.setExamination(examination,this.id);
}



public void setVitals(Set<Vital> vitals){
 vitalrequest.setVitals(vitals,this.id);
}



public Register setOutdoorRegister(Long outdoorRegister){
    this.outdoorRegister = outdoorRegister;
    return this;
}


public void setPatient(Patient patient){
 patientrequest.setPatient(patient,this.id);
}



public void setChiefComplaint(ChiefComplaint chiefComplaint){
 chiefcomplaintrequest.setChiefComplaint(chiefComplaint,this.id);
}



public TreatmentPlan getTreatmentPlan(){
  this.treatmentPlan = treatmentplanrequest.getTreatmentPlan(this.id);
return this.treatmentPlan;
}


public String getBedNumber(){
    return bedNumber;
}


public void setUnit(String unit){
    this.unit = unit;
}


public LifeStyle getLifeStyle(){
  this.lifeStyle = lifestylerequest.getLifeStyle(this.id);
return this.lifeStyle;
}


public Long getOutdoorRegister(){
    return outdoorRegister;
}


public Long getVersion(){
    return version;
}


public void setMedicalHistory(MedicalHistory medicalHistory){
 medicalhistoryrequest.setMedicalHistory(medicalHistory,this.id);
}



public String getRegistrationId(){
    return registrationId;
}


public Patient getPatient(){
  this.patient = patientrequest.getPatient(this.id);
return this.patient;
}


public void setStatus(Status status){
    this.status = status;
}


public void setInvestigation(Set<Investigation> investigation){
 investigationrequest.setInvestigation(investigation,this.id);
}



public Date getAdmissionDate(){
    return admissionDate;
}


public Ward getWard(){
    return ward;
}


public String getWardOther(){
    return wardOther;
}


public PictureInformation getPictureInformation(){
  this.pictureInformation = pictureinformationrequest.getPictureInformation(this.id);
return this.pictureInformation;
}


@Override
public String toString(){
    return "Register{" + "registrationId='" + registrationId + '\'' + ", id=" + id + ", version=" + version + ", ward=" + ward + ", admissionDate=" + admissionDate + ", wardOther='" + wardOther + '\'' + ", bedNumber='" + bedNumber + '\'' + ", unit='" + unit + '\'' + '}';
}


public String getUnit(){
    return unit;
}


public Diagnosis getDiagnosis(){
    return diagnosis;
}


public Date getStopDatetime(){
    return stopDatetime;
}


}