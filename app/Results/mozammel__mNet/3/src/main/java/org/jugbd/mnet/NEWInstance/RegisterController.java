package org.jugbd.mnet.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RegisterController {

 private RegisterDao registerdao;


@PutMapping
("/setExamination/{id}")
public void setExamination(@PathVariable(name = "id") Long id,@RequestParam(name = "examination") Examination examination){
 registerdao.setExamination(id,examination);
}


@PutMapping
("/setTreatmentPlan/{id}")
public void setTreatmentPlan(@PathVariable(name = "id") Long id,@RequestParam(name = "treatmentPlan") TreatmentPlan treatmentPlan){
 registerdao.setTreatmentPlan(id,treatmentPlan);
}


@PutMapping
("/setPictureInformation/{id}")
public void setPictureInformation(@PathVariable(name = "id") Long id,@RequestParam(name = "pictureInformation") PictureInformation pictureInformation){
 registerdao.setPictureInformation(id,pictureInformation);
}


@PutMapping
("/setMedicalHistory/{id}")
public void setMedicalHistory(@PathVariable(name = "id") Long id,@RequestParam(name = "medicalHistory") MedicalHistory medicalHistory){
 registerdao.setMedicalHistory(id,medicalHistory);
}


@PutMapping
("/setChiefComplaint/{id}")
public void setChiefComplaint(@PathVariable(name = "id") Long id,@RequestParam(name = "chiefComplaint") ChiefComplaint chiefComplaint){
 registerdao.setChiefComplaint(id,chiefComplaint);
}


@PutMapping
("/setLifeStyle/{id}")
public void setLifeStyle(@PathVariable(name = "id") Long id,@RequestParam(name = "lifeStyle") LifeStyle lifeStyle){
 registerdao.setLifeStyle(id,lifeStyle);
}


@PutMapping
("/setComplicationManagement/{id}")
public void setComplicationManagement(@PathVariable(name = "id") Long id,@RequestParam(name = "complicationManagement") ComplicationManagement complicationManagement){
 registerdao.setComplicationManagement(id,complicationManagement);
}


}