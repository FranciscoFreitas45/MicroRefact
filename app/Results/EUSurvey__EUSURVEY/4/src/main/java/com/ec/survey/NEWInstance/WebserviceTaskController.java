package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WebserviceTaskController {

 private WebserviceTask webservicetask;

 private WebserviceTask webservicetask;


@PutMapping
("/setSurveyId")
public void setSurveyId(@RequestParam(name = "surveyId") int surveyId){
webservicetask.setSurveyId(surveyId);
}


@PutMapping
("/setSurveyUid")
public void setSurveyUid(@RequestParam(name = "surveyUid") String surveyUid){
webservicetask.setSurveyUid(surveyUid);
}


@PutMapping
("/setExportType")
public void setExportType(@RequestParam(name = "exportType") Integer exportType){
webservicetask.setExportType(exportType);
}


@PutMapping
("/setFileTypes")
public void setFileTypes(@RequestParam(name = "fileTypes") String fileTypes){
webservicetask.setFileTypes(fileTypes);
}


@PutMapping
("/setContributionType")
public void setContributionType(@RequestParam(name = "contributionType") String contributionType){
webservicetask.setContributionType(contributionType);
}


@PutMapping
("/setStart")
public void setStart(@RequestParam(name = "start") Date start){
webservicetask.setStart(start);
}


@PutMapping
("/setEnd")
public void setEnd(@RequestParam(name = "end") Date end){
webservicetask.setEnd(end);
}


@PutMapping
("/setShowIDs")
public void setShowIDs(@RequestParam(name = "showIDs") boolean showIDs){
webservicetask.setShowIDs(showIDs);
}


@PutMapping
("/setUser")
public void setUser(@RequestParam(name = "user") User user){
webservicetask.setUser(user);
}


@PutMapping
("/setCreated")
public void setCreated(@RequestParam(name = "created") Date created){
webservicetask.setCreated(created);
}


@PutMapping
("/setAddMeta")
public void setAddMeta(@RequestParam(name = "addMeta") boolean addMeta){
webservicetask.setAddMeta(addMeta);
}


@PutMapping
("/setGroupId")
public void setGroupId(@RequestParam(name = "groupId") int groupId){
webservicetask.setGroupId(groupId);
}


@PutMapping
("/setNumber")
public void setNumber(@RequestParam(name = "number") int number){
webservicetask.setNumber(number);
}


@PutMapping
("/setToken")
public void setToken(@RequestParam(name = "token") String token){
webservicetask.setToken(token);
}


@GetMapping
("/isDone")
public boolean isDone(){
  return webservicetask.isDone();
}


@GetMapping
("/isEmpty")
public Boolean isEmpty(){
  return webservicetask.isEmpty();
}


}