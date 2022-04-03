package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TranslationServiceController {

 private TranslationService translationservice;


@GetMapping
("/getTranslationLanguagesForSurvey")
public List<String> getTranslationLanguagesForSurvey(@RequestParam(name = "surveyId") Integer surveyId,@RequestParam(name = "onlyActive") boolean onlyActive){
  return translationservice.getTranslationLanguagesForSurvey(surveyId,onlyActive);
}


}