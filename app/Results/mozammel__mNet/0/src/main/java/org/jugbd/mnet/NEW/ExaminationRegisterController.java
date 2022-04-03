package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.Examination;
@RestController
@CrossOrigin
public class ExaminationRegisterController {

@Autowired
 private ExaminationRegisterService examinationregisterservice;


@GetMapping
("/Register/{id}/Examination/getExamination")
public Examination getExamination(@PathVariable(name="id") Long id){
return examinationregisterservice.getExamination(id);
}


@PutMapping
("/Register/{id}/Examination/setExamination")
public void setExamination(@PathVariable(name="id") Long id,@RequestBody Examination examination){
examinationregisterservice.setExamination(id,examination);
}


}