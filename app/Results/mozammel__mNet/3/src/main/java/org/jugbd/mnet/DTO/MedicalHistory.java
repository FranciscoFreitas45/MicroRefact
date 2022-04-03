package org.jugbd.mnet.DTO;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jugbd.mnet.domain.enums.MenstrualCycle;
import org.jugbd.mnet.domain.enums.PastMedicalHistory;
import javax.persistence;
import javax.validation.constraints.Size;
public class MedicalHistory extends PersistentObjectimplements Auditable{

 private  Long id;

 private  Long version;

 private  PastMedicalHistory pastMedicalHistory;

 private  Integer period;

 private  Integer days;

 private  MenstrualCycle menstrualCycle;

 private  String pastSurgicalHistory;

 private  String drugHistory;

 private  String familyHistory;

 private  Boolean similarDiseasesInFamily;

 private  String presentIllness;

 private  String comments;

 private  Register register;


public Long getId(){
    return id;
}


public Integer getPeriod(){
    return period;
}


public PastMedicalHistory getPastMedicalHistory(){
    return pastMedicalHistory;
}


public MenstrualCycle getMenstrualCycle(){
    return menstrualCycle;
}


public String getComments(){
    return comments;
}


public Long getVersion(){
    return version;
}


public String getPastSurgicalHistory(){
    return pastSurgicalHistory;
}


public String getDrugHistory(){
    return drugHistory;
}


public Register getRegister(){
    return register;
}


public Boolean getSimilarDiseasesInFamily(){
    return similarDiseasesInFamily;
}


public Integer getDays(){
    return days;
}


public String getFamilyHistory(){
    return familyHistory;
}


public String getPresentIllness(){
    return presentIllness;
}


}