package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.OutdoorRegister;
@RestController
@CrossOrigin
public class OutdoorRegisterTreatmentPlanController {

@Autowired
 private OutdoorRegisterTreatmentPlanService outdoorregistertreatmentplanservice;


@GetMapping
("/TreatmentPlan/{id}/OutdoorRegister/setOutdoorRegister")
public TreatmentPlan setOutdoorRegister(@PathVariable(name="id") Long id,@RequestParam OutdoorRegister outdoorRegister){
return outdoorregistertreatmentplanservice.setOutdoorRegister(id,outdoorRegister);
}


@GetMapping
("/TreatmentPlan/{id}/OutdoorRegister/getOutdoorRegister")
public OutdoorRegister getOutdoorRegister(@PathVariable(name="id") Long id){
return outdoorregistertreatmentplanservice.getOutdoorRegister(id);
}


}