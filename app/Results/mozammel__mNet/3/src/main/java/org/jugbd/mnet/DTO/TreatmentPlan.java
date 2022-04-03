package org.jugbd.mnet.DTO;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jugbd.mnet.domain.enums.TreatmentPlanType;
import javax.persistence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.jugbd.mnet.Request.RegisterRequest;
import org.jugbd.mnet.Request.Impl.RegisterRequestImpl;
import org.jugbd.mnet.DTO.Register;
import org.jugbd.mnet.Request.OutdoorRegisterRequest;
import org.jugbd.mnet.Request.Impl.OutdoorRegisterRequestImpl;
import org.jugbd.mnet.DTO.OutdoorRegister;
public class TreatmentPlan extends PersistentObjectimplements Auditable{

 private  Long id;

 private  TreatmentPlanType treatmentPlanType;

 private  String otherTreatmentPlanType;

 private  String typeOfConservativeTreatment;

 private  Boolean stsgOrFtsg;

 private  Boolean flapPedicled;

 private  Boolean freeFlap;

 private  Boolean tissueExpansion;

 private  Boolean fasciotomyOrEscharotomy;

 private  Boolean implantInsertion;

 private  Boolean repairReconstructionPart;

 private  String comment;

 private  Register register;

 private  OutdoorRegister outdoorRegister;

 private Long id;

 private Long id;


public Boolean getFlapPedicled(){
    return flapPedicled;
}


public Long getId(){
    return id;
}


public Boolean getFasciotomyOrEscharotomy(){
    return fasciotomyOrEscharotomy;
}


public Boolean getFreeFlap(){
    return freeFlap;
}


public Boolean getTissueExpansion(){
    return tissueExpansion;
}


public Boolean getImplantInsertion(){
    return implantInsertion;
}


public Boolean getRepairReconstructionPart(){
    return repairReconstructionPart;
}


public String getComment(){
    return comment;
}


public OutdoorRegister getOutdoorRegister(){
  this.outdoorRegister = outdoorregisterrequest.getOutdoorRegister(this.id);
return this.outdoorRegister;
}


public TreatmentPlanType getTreatmentPlanType(){
    return treatmentPlanType;
}


public String getTypeOfConservativeTreatment(){
    return typeOfConservativeTreatment;
}


public Register getRegister(){
  this.register = registerrequest.getRegister(this.id);
return this.register;
}


public Boolean getStsgOrFtsg(){
    return stsgOrFtsg;
}


public String getOtherTreatmentPlanType(){
    return otherTreatmentPlanType;
}


}