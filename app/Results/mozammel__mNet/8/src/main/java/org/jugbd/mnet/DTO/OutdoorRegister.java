package org.jugbd.mnet.DTO;
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

 private Long id;

 private Long id;

 private Long id;

 private Long id;


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


public Long getId(){
    return id;
}


public Status getStatus(){
    return status;
}


public Set<Visit> getVisits(){
  this.visits = visitrequest.getVisits(this.id);
return this.visits;
}


public TreatmentPlan getTreatmentPlan(){
  this.treatmentPlan = treatmentplanrequest.getTreatmentPlan(this.id);
return this.treatmentPlan;
}


public Date getStartDatetime(){
    return startDatetime;
}


public ChiefComplaint getChiefComplaint(){
  this.chiefComplaint = chiefcomplaintrequest.getChiefComplaint(this.id);
return this.chiefComplaint;
}


public Long getVersion(){
    return version;
}


public String getRemarks(){
    return remarks;
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


public PictureInformation getPictureInformation(){
  this.pictureInformation = pictureinformationrequest.getPictureInformation(this.id);
return this.pictureInformation;
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


}