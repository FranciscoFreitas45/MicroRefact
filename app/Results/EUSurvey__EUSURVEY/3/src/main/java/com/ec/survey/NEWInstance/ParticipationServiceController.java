package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ParticipationServiceController {

 private ParticipationService participationservice;


@GetMapping
("/get")
public ParticipationGroup get(@RequestParam(name = "id") Integer id){
  return participationservice.get(id);
}


}