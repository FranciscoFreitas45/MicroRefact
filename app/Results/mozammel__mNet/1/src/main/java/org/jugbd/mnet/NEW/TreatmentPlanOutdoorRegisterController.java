package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.TreatmentPlan;
@RestController
@CrossOrigin
public class TreatmentPlanOutdoorRegisterController {

@Autowired
 private TreatmentPlanOutdoorRegisterService treatmentplanoutdoorregisterservice;


@GetMapping
("/OutdoorRegister/{id}/TreatmentPlan/setTreatmentPlan")
public OutdoorRegister setTreatmentPlan(@PathVariable(name="id") Long id,@RequestParam TreatmentPlan treatmentPlan){
return treatmentplanoutdoorregisterservice.setTreatmentPlan(id,treatmentPlan);
}


@GetMapping
("/OutdoorRegister/{id}/TreatmentPlan/getTreatmentPlan")
public TreatmentPlan getTreatmentPlan(@PathVariable(name="id") Long id){
return treatmentplanoutdoorregisterservice.getTreatmentPlan(id);
}


}