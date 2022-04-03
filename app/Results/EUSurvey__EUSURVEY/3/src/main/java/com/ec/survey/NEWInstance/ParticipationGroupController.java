package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ParticipationGroupController {

 private ParticipationGroup participationgroup;

 private ParticipationGroup participationgroup;


@PutMapping
("/setActive")
public void setActive(@RequestParam(name = "active") Boolean active){
participationgroup.setActive(active);
}


@PutMapping
("/setOwnerId")
public void setOwnerId(@RequestParam(name = "ownerId") Integer ownerId){
participationgroup.setOwnerId(ownerId);
}


@PutMapping
("/setName")
public void setName(@RequestParam(name = "name") String name){
participationgroup.setName(name);
}


@PutMapping
("/setSurveyId")
public void setSurveyId(@RequestParam(name = "surveyId") Integer surveyId){
participationgroup.setSurveyId(surveyId);
}


@PutMapping
("/setType")
public void setType(@RequestParam(name = "type") ParticipationGroupType type){
participationgroup.setType(type);
}


}