package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TranslationsController {

 private Translations translations;

 private Translations translations;


@PutMapping
("/setActive")
public void setActive(@RequestParam(name = "active") Boolean active){
translations.setActive(active);
}


@GetMapping
("/removeTranslationByKey")
public boolean removeTranslationByKey(@RequestParam(name = "key") String key){
  return translations.removeTranslationByKey(key);
}


@PutMapping
("/setComplete")
public void setComplete(@RequestParam(name = "complete") Boolean complete){
translations.setComplete(complete);
}


@PutMapping
("/setLanguage")
public void setLanguage(@RequestParam(name = "language") Language language){
translations.setLanguage(language);
}


@PutMapping
("/setTitle")
public void setTitle(@RequestParam(name = "title") String title){
translations.setTitle(title);
}


@PutMapping
("/setSurveyId")
public void setSurveyId(@RequestParam(name = "surveyId") Integer surveyId){
translations.setSurveyId(surveyId);
}


@PutMapping
("/setSurveyUid")
public void setSurveyUid(@RequestParam(name = "surveyUid") String surveyUid){
translations.setSurveyUid(surveyUid);
}


}