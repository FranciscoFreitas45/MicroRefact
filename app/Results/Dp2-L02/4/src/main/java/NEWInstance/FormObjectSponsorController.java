package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FormObjectSponsorController {

 private FormObjectSponsor formobjectsponsor;

 private FormObjectSponsor formobjectsponsor;


@PutMapping
("/setTermsAndConditions")
public void setTermsAndConditions(@RequestParam(name = "termsAndConditions") Boolean termsAndConditions){
formobjectsponsor.setTermsAndConditions(termsAndConditions);
}


}