package io.swagger.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.model.Medic;
@RestController
@CrossOrigin
public class MedicSessionController {

@Autowired
 private MedicSessionService medicsessionservice;


@GetMapping
("/Session/{id}/Medic/getMedic")
public Medic getMedic(@PathVariable(name="id") Long id){
return medicsessionservice.getMedic(id);
}


@PutMapping
("/Session/{id}/Medic/setMedic")
public void setMedic(@PathVariable(name="id") Long id,@RequestBody Medic medic){
medicsessionservice.setMedic(id,medic);
}


}