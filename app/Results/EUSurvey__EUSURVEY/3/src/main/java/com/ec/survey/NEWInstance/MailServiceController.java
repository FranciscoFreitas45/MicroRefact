package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MailServiceController {

 private MailService mailservice;


@PutMapping
("/SendHtmlMail")
public void SendHtmlMail(@RequestParam(name = "to") String to,@RequestParam(name = "from") String from,@RequestParam(name = "reply") String reply,@RequestParam(name = "subject") String subject,@RequestParam(name = "body") String body,@RequestParam(name = "info") String info){
mailservice.SendHtmlMail(to,from,reply,subject,body,info);
}


@GetMapping
("/getFirstFinishedMailTask")
public MailTask getFirstFinishedMailTask(@RequestParam(name = "surveyUid") String surveyUid){
  return mailservice.getFirstFinishedMailTask(surveyUid);
}


@PutMapping
("/save")
public void save(@RequestParam(name = "task") MailTask task){
mailservice.save(task);
}


}