package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PDFServiceController {

 private PDFService pdfservice;


@GetMapping
("/createAnswerPDF")
public java.io.File createAnswerPDF(@RequestParam(name = "answerSetId") Integer answerSetId,@RequestParam(name = "uniqueCode") String uniqueCode,@RequestParam(name = "surveyUid") String surveyUid,@RequestParam(name = "isDraft") boolean isDraft){
  return pdfservice.createAnswerPDF(answerSetId,uniqueCode,surveyUid,isDraft);
}


@GetMapping
("/createDraftAnswerPDF")
public String createDraftAnswerPDF(@RequestParam(name = "code") String code,@RequestParam(name = "email") String email){
  return pdfservice.createDraftAnswerPDF(code,email);
}


@GetMapping
("/createSurveyPDF")
public java.io.File createSurveyPDF(@RequestParam(name = "survey") Survey survey,@RequestParam(name = "lang") String lang,@RequestParam(name = "target") java.io.File target){
  return pdfservice.createSurveyPDF(survey,lang,target);
}


}