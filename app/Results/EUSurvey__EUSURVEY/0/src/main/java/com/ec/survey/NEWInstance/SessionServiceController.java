package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SessionServiceController {

 private SessionService sessionservice;


@PutMapping
("/ClearUniqueCodeForForm")
public void ClearUniqueCodeForForm(@RequestParam(name = "request") HttpServletRequest request,@RequestParam(name = "surveyId") int surveyId){
sessionservice.ClearUniqueCodeForForm(request,surveyId);
}


@PutMapping
("/initializeProxy")
public void initializeProxy(){
sessionservice.initializeProxy();
}


@GetMapping
("/getCaptchaText")
public String getCaptchaText(@RequestParam(name = "request") HttpServletRequest request){
  return sessionservice.getCaptchaText(request);
}


@GetMapping
("/getPdfServerPrefix")
public String getPdfServerPrefix(){
  return sessionservice.getPdfServerPrefix();
}


@GetMapping
("/getContextPath")
public String getContextPath(){
  return sessionservice.getContextPath();
}


}