import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class SpecialityValidationServiceController {

 private SpecialityValidationService specialityvalidationservice;


@GetMapping
("/clear")
public SpecialityValidationService clear(){
  return specialityvalidationservice.clear();
}


@GetMapping
("/setSpeciality")
public SpecialityValidationService setSpeciality(@RequestParam(name = "speciality") Speciality speciality){
  return specialityvalidationservice.setSpeciality(speciality);
}


@GetMapping
("/validName")
public SpecialityValidationService validName(){
  return specialityvalidationservice.validName();
}


@GetMapping
("/validLength")
public SpecialityValidationService validLength(){
  return specialityvalidationservice.validLength();
}


@GetMapping
("/validPrice")
public SpecialityValidationService validPrice(){
  return specialityvalidationservice.validPrice();
}


@GetMapping
("/isValid")
public boolean isValid(){
  return specialityvalidationservice.isValid();
}


@GetMapping
("/getErrorModelMap")
public ModelMap getErrorModelMap(){
  return specialityvalidationservice.getErrorModelMap();
}


}