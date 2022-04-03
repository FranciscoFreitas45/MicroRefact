package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TranslationController {

 private Translation translation;

 private Translation translation;


@PutMapping
("/setLabel")
public void setLabel(@RequestParam(name = "label") String label){
translation.setLabel(label);
}


@PutMapping
("/setSurveyId")
public void setSurveyId(@RequestParam(name = "surveyId") Integer surveyId){
translation.setSurveyId(surveyId);
}


@PutMapping
("/setKey")
public void setKey(@RequestParam(name = "key") String key){
translation.setKey(key);
}


@PutMapping
("/setLanguage")
public void setLanguage(@RequestParam(name = "language") String language){
translation.setLanguage(language);
}


@PutMapping
("/setTranslations")
public void setTranslations(@RequestParam(name = "s") Translations s){
translation.setTranslations(s);
}


}