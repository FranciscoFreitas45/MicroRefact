package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.Register;
@RestController
@CrossOrigin
public class RegisterTreatmentPlanController {

@Autowired
 private RegisterTreatmentPlanService registertreatmentplanservice;


@GetMapping
("/TreatmentPlan/{id}/Register/setRegister")
public TreatmentPlan setRegister(@PathVariable(name="id") Long id,@RequestParam Register register){
return registertreatmentplanservice.setRegister(id,register);
}


@GetMapping
("/TreatmentPlan/{id}/Register/getRegister")
public Register getRegister(@PathVariable(name="id") Long id){
return registertreatmentplanservice.getRegister(id);
}


}