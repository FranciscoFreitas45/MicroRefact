package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class InvitationController {

 private Invitation invitation;

 private Invitation invitation;


@PutMapping
("/setAnswers")
public void setAnswers(@RequestParam(name = "answers") int answers){
invitation.setAnswers(answers);
}


@PutMapping
("/setDeactivated")
public void setDeactivated(@RequestParam(name = "deactivated") Boolean deactivated){
invitation.setDeactivated(deactivated);
}


}