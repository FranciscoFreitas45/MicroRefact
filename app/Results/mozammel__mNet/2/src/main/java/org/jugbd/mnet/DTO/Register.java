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


public ComplicationManagement getComplicationManagement(){
    return complicationManagement;
}


public Status getStatus(){
    return status;
}


public Set<Visit> getVisits(){
    return visits;
}


public MedicalHistory getMedicalHistory(){
    return medicalHistory;
}


public Set<Investigation> getInvestigation(){
    return investigation;
}


public Date getStartDatetime(){
    return startDatetime;
}


public ChiefComplaint getChiefComplaint(){
    return chiefComplaint;
}


public Set<OperationalDetail> getOperationalDetails(){
    return operationalDetails;
}


public Set<Vital> getVitals(){
    return vitals;
}


public PatientContact getPatientContact(){
    return patientContact;
}


public Examination getExamination(){
    return examination;
}


public Long getId(){
    return id;
}


public TreatmentPlan getTreatmentPlan(){
    return treatmentPlan;
}


public String getBedNumber(){
    return bedNumber;
}


public LifeStyle getLifeStyle(){
    return lifeStyle;
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
    return patient;
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
    return pictureInformation;
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