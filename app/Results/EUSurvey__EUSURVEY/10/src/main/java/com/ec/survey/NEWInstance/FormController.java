package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FormController {

 private Form form;


@PutMapping
("/setSurvey")
public void setSurvey(@RequestParam(name = "survey") Survey survey){
form.setSurvey(survey);
}


@PutMapping
("/setStatistics")
public void setStatistics(@RequestParam(name = "statistics") Statistics statistics){
form.setStatistics(statistics);
}


@GetMapping
("/getSurvey")
public Survey getSurvey(){
  return form.getSurvey();
}


@PutMapping
("/setNumberOfAnswerSets")
public void setNumberOfAnswerSets(@RequestParam(name = "numberOfAnswerSets") long numberOfAnswerSets){
form.setNumberOfAnswerSets(numberOfAnswerSets);
}


@PutMapping
("/setLanguage")
public void setLanguage(@RequestParam(name = "language") Language language){
form.setLanguage(language);
}


@PutMapping
("/setWcagCompliance")
public void setWcagCompliance(@RequestParam(name = "b") boolean b){
form.setWcagCompliance(b);
}


@PutMapping
("/setValidation")
public void setValidation(@RequestParam(name = "validation") Map<Element,String> validation){
form.setValidation(validation);
}


@GetMapping
("/translationIsValid")
public boolean translationIsValid(@RequestParam(name = "lang") String lang){
  return form.translationIsValid(lang);
}


@PutMapping
("/setForPDF")
public void setForPDF(@RequestParam(name = "forPDF") boolean forPDF){
form.setForPDF(forPDF);
}


@PutMapping
("/setLanguages")
public void setLanguages(@RequestParam(name = "languages") Map<String,Language> languages){
form.setLanguages(languages);
}


@PutMapping
("/setResources")
public void setResources(@RequestParam(name = "resources") MessageSource resources){
form.setResources(resources);
}


@PutMapping
("/setUploadItem")
public void setUploadItem(@RequestParam(name = "uploadItem") UploadItem uploadItem){
form.setUploadItem(uploadItem);
}


@PutMapping
("/setStartHour")
public void setStartHour(@RequestParam(name = "startHour") int startHour){
form.setStartHour(startHour);
}


@PutMapping
("/setEndHour")
public void setEndHour(@RequestParam(name = "endHour") int endHour){
form.setEndHour(endHour);
}


@PutMapping
("/setAnswerSets")
public void setAnswerSets(@RequestParam(name = "answerSets") List<AnswerSet> answerSets){
form.setAnswerSets(answerSets);
}


@PutMapping
("/setPublicationMode")
public void setPublicationMode(@RequestParam(name = "publicationMode") boolean publicationMode){
form.setPublicationMode(publicationMode);
}


}