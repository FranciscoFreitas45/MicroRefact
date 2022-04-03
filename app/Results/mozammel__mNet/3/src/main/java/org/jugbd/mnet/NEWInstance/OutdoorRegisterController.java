package org.jugbd.mnet.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OutdoorRegisterController {

 private OutdoorRegisterRepository outdoorregisterrepository;


@GetMapping
("/setExamination/{id}")
public OutdoorRegister setExamination(@PathVariable(name = "id") Long id,@RequestParam(name = "examination") Examination examination){
 return outdoorregisterrepository.setExamination(id,examination);
}


@GetMapping
("/setTreatmentPlan/{id}")
public OutdoorRegister setTreatmentPlan(@PathVariable(name = "id") Long id,@RequestParam(name = "treatmentPlan") TreatmentPlan treatmentPlan){
 return outdoorregisterrepository.setTreatmentPlan(id,treatmentPlan);
}


@GetMapping
("/setChiefComplaint/{id}")
public OutdoorRegister setChiefComplaint(@PathVariable(name = "id") Long id,@RequestParam(name = "chiefComplaint") ChiefComplaint chiefComplaint){
 return outdoorregisterrepository.setChiefComplaint(id,chiefComplaint);
}


}