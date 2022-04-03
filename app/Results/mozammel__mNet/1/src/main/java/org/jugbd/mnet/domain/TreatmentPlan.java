package org.jugbd.mnet.domain;
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
@Entity
public class TreatmentPlan extends PersistentObjectimplements Auditable{

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@NotNull
@Column(length = 16)
@Enumerated(EnumType.STRING)
 private  TreatmentPlanType treatmentPlanType;

@Size(max = 500)
 private  String otherTreatmentPlanType;

@Size(max = 500)
 private  String typeOfConservativeTreatment;

 private  Boolean stsgOrFtsg;

 private  Boolean flapPedicled;

 private  Boolean freeFlap;

 private  Boolean tissueExpansion;

 private  Boolean fasciotomyOrEscharotomy;

 private  Boolean implantInsertion;

 private  Boolean repairReconstructionPart;

@Size(max = 1000)
 private  String comment;

@Transient
 private  Register register;

@Transient
 private  OutdoorRegister outdoorRegister;

@Column(name = "id")
 private Long id;

@Transient
 private RegisterRequest registerrequest = new RegisterRequestImpl();;

@Column(name = "id")
 private Long id;

@Transient
 private OutdoorRegisterRequest outdoorregisterrequest = new OutdoorRegisterRequestImpl();;


public TreatmentPlan setTypeOfConservativeTreatment(String typeOfConservativeTreatment){
    this.typeOfConservativeTreatment = typeOfConservativeTreatment;
    return this;
}


public void setTreatmentPlanType(TreatmentPlanType treatmentPlanType){
    this.treatmentPlanType = treatmentPlanType;
}


public Boolean getFlapPedicled(){
    return flapPedicled;
}


public void setFlapPedicled(Boolean flapPedicled){
    this.flapPedicled = flapPedicled;
}


public void setFasciotomyOrEscharotomy(Boolean fasciotomyOrEscharotomy){
    this.fasciotomyOrEscharotomy = fasciotomyOrEscharotomy;
}


public Long getId(){
    return id;
}


public TreatmentPlan setRegister(Register register){
  this.register = registerrequest.setRegister(register,this.id);
return this.register;
}


public void setOtherTreatmentPlanType(String otherTreatmentPlanType){
    this.otherTreatmentPlanType = otherTreatmentPlanType;
}


public Boolean getFasciotomyOrEscharotomy(){
    return fasciotomyOrEscharotomy;
}


public TreatmentPlan setOutdoorRegister(OutdoorRegister outdoorRegister){
  this.outdoorRegister = outdoorregisterrequest.setOutdoorRegister(outdoorRegister,this.id);
return this.outdoorRegister;
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


public void setFreeFlap(Boolean freeFlap){
    this.freeFlap = freeFlap;
}


public void setId(Long id){
    this.id = id;
}


public void setStsgOrFtsg(Boolean stsgOrFtsg){
    this.stsgOrFtsg = stsgOrFtsg;
}


public String getComment(){
    return comment;
}


public OutdoorRegister getOutdoorRegister(){
  this.outdoorRegister = outdoorregisterrequest.getOutdoorRegister(this.id);
return this.outdoorRegister;
}


public void setTissueExpansion(Boolean tissueExpansion){
    this.tissueExpansion = tissueExpansion;
}


public TreatmentPlanType getTreatmentPlanType(){
    return treatmentPlanType;
}


public String getTypeOfConservativeTreatment(){
    return typeOfConservativeTreatment;
}


public void setImplantInsertion(Boolean implantInsertion){
    this.implantInsertion = implantInsertion;
}


public Register getRegister(){
  this.register = registerrequest.getRegister(this.id);
return this.register;
}


public Boolean getStsgOrFtsg(){
    return stsgOrFtsg;
}


public void setComment(String comment){
    this.comment = comment;
}


public String getOtherTreatmentPlanType(){
    return otherTreatmentPlanType;
}


public void setRepairReconstructionPart(Boolean repairReconstructionPart){
    this.repairReconstructionPart = repairReconstructionPart;
}


}