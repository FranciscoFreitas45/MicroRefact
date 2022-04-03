package ar.com.veterinaria.app.entities.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.veterinaria.app.entities.ClinicalHistory;
@RestController
@CrossOrigin
public class ClinicalHistoryPetController {

@Autowired
 private ClinicalHistoryPetService clinicalhistorypetservice;


@PutMapping
("/Pet/{id}/ClinicalHistory/setClinicalHistory")
public void setClinicalHistory(@PathVariable(name="id") Integer id,@RequestBody ClinicalHistory clinicalHistory){
clinicalhistorypetservice.setClinicalHistory(id,clinicalHistory);
}


@GetMapping
("/Pet/{id}/ClinicalHistory/getClinicalHistory")
public ClinicalHistory getClinicalHistory(@PathVariable(name="id") Integer id){
return clinicalhistorypetservice.getClinicalHistory(id);
}


}