package org.jugbd.mnet.DTO;
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
public class Register extends PersistentObject{

 private  Long id;

 private  Long version;

 private  String registrationId;

 private  Date admissionDate;

 private  Ward ward;

 private  String wardOther;

 private  String bedNumber;

 private  String unit;

 private  Patient patient;

 private  Date startDatetime;

 private  Date stopDatetime;

 private  Set<Vital> vitals;

 private  Set<OperationalDetail> operationalDetails;

 private  Set<Visit> visits;

 private  Status status;

 private  MedicalHistory medicalHistory;

 private  ChiefComplaint chiefComplaint;

 private  Examination examination;

 private  Diagnosis diagnosis;

 private  TreatmentPlan treatmentPlan;

 private  ComplicationManagement complicationManagement;

 private  LifeStyle lifeStyle;

 private  Set<Investigation> investigation;

 private  PictureInformation pictureInformation;

 private  PatientContact patientContact;

 private  Long outdoorRegister;

 private Long id;

 private Long id;

 private Long id;

 private Long id;

 private Long id;

 private Long id;

 private Long id;

 private Long id;


public ComplicationManagement getComplicationManagement(){
  this.complicationManagement = complicationmanagementrequest.getComplicationManagement(this.id);
return this.complicationManagement;
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


public Date getStartDatetime(){
    return startDatetime;
}


public ChiefComplaint getChiefComplaint(){
  this.chiefComplaint = chiefcomplaintrequest.getChiefComplaint(this.id);
return this.chiefComplaint;
}


public Set<OperationalDetail> getOperationalDetails(){
  this.operationalDetails = operationaldetailrequest.getOperationalDetails(this.id);
return this.operationalDetails;
}


public Set<Vital> getVitals(){
  this.vitals = vitalrequest.getVitals(this.id);
return this.vitals;
}


public PatientContact getPatientContact(){
    return patientContact;
}


public Examination getExamination(){
  this.examination = examinationrequest.getExamination(this.id);
return this.examination;
}


public Long getId(){
    return id;
}


public TreatmentPlan getTreatmentPlan(){
  this.treatmentPlan = treatmentplanrequest.getTreatmentPlan(this.id);
return this.treatmentPlan;
}


public String getBedNumber(){
    return bedNumber;
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


public String getRegistrationId(){
    return registrationId;
}


public Patient getPatient(){
  this.patient = patientrequest.getPatient(this.id);
return this.patient;
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