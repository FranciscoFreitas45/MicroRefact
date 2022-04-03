package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ConfirmationController {

 private Confirmation confirmation;

 private Confirmation confirmation;


@PutMapping
("/setConfirmationlabel")
public void setConfirmationlabel(@RequestParam(name = "confirmationlabel") String confirmationlabel){
confirmation.setConfirmationlabel(confirmationlabel);
}


}