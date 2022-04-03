package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UsersConfigurationController {

 private UsersConfiguration usersconfiguration;

 private UsersConfiguration usersconfiguration;


@PutMapping
("/setShowName")
public void setShowName(@RequestParam(name = "showName") boolean showName){
usersconfiguration.setShowName(showName);
}


@PutMapping
("/setShowEmail")
public void setShowEmail(@RequestParam(name = "showEmail") boolean showEmail){
usersconfiguration.setShowEmail(showEmail);
}


@PutMapping
("/setShowOtherEmail")
public void setShowOtherEmail(@RequestParam(name = "showOtherEmail") Boolean showOtherEmail){
usersconfiguration.setShowOtherEmail(showOtherEmail);
}


@PutMapping
("/setShowLanguage")
public void setShowLanguage(@RequestParam(name = "showLanguage") boolean showLanguage){
usersconfiguration.setShowLanguage(showLanguage);
}


@PutMapping
("/setShowRoles")
public void setShowRoles(@RequestParam(name = "showRoles") boolean showRoles){
usersconfiguration.setShowRoles(showRoles);
}


@PutMapping
("/setShowComment")
public void setShowComment(@RequestParam(name = "showComment") boolean showComment){
usersconfiguration.setShowComment(showComment);
}


}