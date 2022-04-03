package org.jugbd.mnet.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.domain.Examination;
@RestController
@CrossOrigin
public class ExaminationOutdoorRegisterController {

@Autowired
 private ExaminationOutdoorRegisterService examinationoutdoorregisterservice;


@GetMapping
("/OutdoorRegister/{id}/Examination/getExamination")
public Examination getExamination(@PathVariable(name="id") Long id){
return examinationoutdoorregisterservice.getExamination(id);
}


@GetMapping
("/OutdoorRegister/{id}/Examination/setExamination")
public OutdoorRegister setExamination(@PathVariable(name="id") Long id,@RequestParam Examination examination){
return examinationoutdoorregisterservice.setExamination(id,examination);
}


}