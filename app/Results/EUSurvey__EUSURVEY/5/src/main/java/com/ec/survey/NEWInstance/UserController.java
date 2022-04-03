package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserController {

 private User user;


@GetMapping
("/getId")
public Integer getId(){
  return user.getId();
}


@GetMapping
("/getGlobalPrivileges")
public Map<GlobalPrivilege,Integer> getGlobalPrivileges(){
  return user.getGlobalPrivileges();
}


@GetMapping
("/get")
public Object get(@RequestParam(name = "Object") Object Object){
  return user.get(Object);
}


@GetMapping
("/getEmail")
public String getEmail(){
  return user.getEmail();
}


@PutMapping
("/setPasswordSalt")
public void setPasswordSalt(@RequestParam(name = "passwordSalt") String passwordSalt){
user.setPasswordSalt(passwordSalt);
}


@PutMapping
("/setValidated")
public void setValidated(@RequestParam(name = "validated") boolean validated){
user.setValidated(validated);
}


@PutMapping
("/setComment")
public void setComment(@RequestParam(name = "comment") String comment){
user.setComment(comment);
}


@PutMapping
("/setLanguage")
public void setLanguage(@RequestParam(name = "language") String language){
user.setLanguage(language);
}


@PutMapping
("/setPassword")
public void setPassword(@RequestParam(name = "password") String password){
user.setPassword(password);
}


@PutMapping
("/setSurName")
public void setSurName(@RequestParam(name = "surName") String surName){
user.setSurName(surName);
}


@PutMapping
("/setGivenName")
public void setGivenName(@RequestParam(name = "givenName") String givenName){
user.setGivenName(givenName);
}


@PutMapping
("/setEmail")
public void setEmail(@RequestParam(name = "email") String email){
user.setEmail(email);
}


@PutMapping
("/setLogin")
public void setLogin(@RequestParam(name = "login") String login){
user.setLogin(login);
}


@PutMapping
("/setType")
public void setType(@RequestParam(name = "type") String type){
user.setType(type);
}


@GetMapping
("/isExternal")
public boolean isExternal(){
  return user.isExternal();
}


@PutMapping
("/setId")
public void setId(@RequestParam(name = "id") Integer id){
user.setId(id);
}


@PutMapping
("/setDisplayName")
public void setDisplayName(@RequestParam(name = "displayName") String displayName){
user.setDisplayName(displayName);
}


@PutMapping
("/setCanCreateSurveys")
public void setCanCreateSurveys(@RequestParam(name = "canCreateSurveys") boolean canCreateSurveys){
user.setCanCreateSurveys(canCreateSurveys);
}


@GetMapping
("/isAgreedToPS")
public boolean isAgreedToPS(){
  return user.isAgreedToPS();
}


@GetMapping
("/isAgreedToToS")
public boolean isAgreedToToS(){
  return user.isAgreedToToS();
}


@PutMapping
("/setLocalPrivileges")
public void setLocalPrivileges(@RequestParam(name = "localPrivileges") Map<LocalPrivilege,Integer> localPrivileges){
user.setLocalPrivileges(localPrivileges);
}


@PutMapping
("/upgradePrivileges")
public void upgradePrivileges(@RequestParam(name = "localPrivileges") Map<LocalPrivilege,Integer> localPrivileges){
user.upgradePrivileges(localPrivileges);
}


@PutMapping
("/setDefaultPivotLanguage")
public void setDefaultPivotLanguage(@RequestParam(name = "defaultPivotLanguage") String defaultPivotLanguage){
user.setDefaultPivotLanguage(defaultPivotLanguage);
}


@PutMapping
("/setUserExistsAttemptDate")
public void setUserExistsAttemptDate(@RequestParam(name = "userExistsAttemptDate") Date userExistsAttemptDate){
user.setUserExistsAttemptDate(userExistsAttemptDate);
}


@PutMapping
("/setUserExistsAttempts")
public void setUserExistsAttempts(@RequestParam(name = "userExistsAttempts") Integer userExistsAttempts){
user.setUserExistsAttempts(userExistsAttempts);
}


@PutMapping
("/setEmailToValidate")
public void setEmailToValidate(@RequestParam(name = "emailToValidate") String emailToValidate){
user.setEmailToValidate(emailToValidate);
}


@PutMapping
("/setDepartments")
public void setDepartments(@RequestParam(name = "departments") List<String> departments){
user.setDepartments(departments);
}


@PutMapping
("/setSelectedAttributesOrder")
public void setSelectedAttributesOrder(@RequestParam(name = "selectedAttributesOrder") String selectedAttributesOrder){
user.setSelectedAttributesOrder(selectedAttributesOrder);
}


}