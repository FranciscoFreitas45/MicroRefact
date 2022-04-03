package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.TreatmentPlan;
@RestController
@CrossOrigin
public class TreatmentPlanRegisterController {

@Autowired
 private TreatmentPlanRegisterService treatmentplanregisterservice;


@PutMapping
("/Register/{id}/TreatmentPlan/setTreatmentPlan")
public void setTreatmentPlan(@PathVariable(name="id") Long id,@RequestBody TreatmentPlan treatmentPlan){
treatmentplanregisterservice.setTreatmentPlan(id,treatmentPlan);
}


@GetMapping
("/Register/{id}/TreatmentPlan/getTreatmentPlan")
public TreatmentPlan getTreatmentPlan(@PathVariable(name="id") Long id){
return treatmentplanregisterservice.getTreatmentPlan(id);
}


}