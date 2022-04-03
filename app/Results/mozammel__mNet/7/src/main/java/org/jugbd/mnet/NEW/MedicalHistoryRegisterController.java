package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.MedicalHistory;
@RestController
@CrossOrigin
public class MedicalHistoryRegisterController {

@Autowired
 private MedicalHistoryRegisterService medicalhistoryregisterservice;


@GetMapping
("/Register/{id}/MedicalHistory/getMedicalHistory")
public MedicalHistory getMedicalHistory(@PathVariable(name="id") Long id){
return medicalhistoryregisterservice.getMedicalHistory(id);
}


@PutMapping
("/Register/{id}/MedicalHistory/setMedicalHistory")
public void setMedicalHistory(@PathVariable(name="id") Long id,@RequestBody MedicalHistory medicalHistory){
medicalhistoryregisterservice.setMedicalHistory(id,medicalHistory);
}


}