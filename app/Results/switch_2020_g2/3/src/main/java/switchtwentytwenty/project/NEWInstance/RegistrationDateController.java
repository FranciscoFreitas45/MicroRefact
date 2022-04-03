package switchtwentytwenty.project.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RegistrationDateController {

 private RegistrationDate registrationdate;


@GetMapping
("/equals")
public boolean equals(@RequestParam(name = "other") Object other){
  return registrationdate.equals(other);
}


@PutMapping
("/setDate")
public void setDate(@RequestParam(name = "dateToInput") Date dateToInput){
registrationdate.setDate(dateToInput);
}


}