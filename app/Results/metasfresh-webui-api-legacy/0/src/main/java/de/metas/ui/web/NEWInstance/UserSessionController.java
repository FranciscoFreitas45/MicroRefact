package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserSessionController {

 private UserSession usersession;


@PutMapping
("/assertLoggedIn")
public void assertLoggedIn(){
usersession.assertLoggedIn();
}


@GetMapping
("/getAD_Language")
public String getAD_Language(){
  return usersession.getAD_Language();
}


@GetMapping
("/toEvaluatee")
public Evaluatee toEvaluatee(){
  return usersession.toEvaluatee();
}


@GetMapping
("/getLoggedUserId")
public UserId getLoggedUserId(){
  return usersession.getLoggedUserId();
}


@GetMapping
("/getOrgId")
public OrgId getOrgId(){
  return usersession.getOrgId();
}


@GetMapping
("/getRepoId")
public Object getRepoId(@RequestParam(name = "Object") Object Object){
  return usersession.getRepoId(Object);
}


@GetMapping
("/isShowColumnNamesForCaption")
public boolean isShowColumnNamesForCaption(){
  return usersession.isShowColumnNamesForCaption();
}


}