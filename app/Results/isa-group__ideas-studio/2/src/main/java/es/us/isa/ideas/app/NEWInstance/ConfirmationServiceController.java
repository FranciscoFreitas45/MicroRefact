package es.us.isa.ideas.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ConfirmationServiceController {

 private ConfirmationService confirmationservice;


@PutMapping
("/createPasswordResetConfirmation")
public void createPasswordResetConfirmation(@RequestParam(name = "email") String email){
confirmationservice.createPasswordResetConfirmation(email);
}


}