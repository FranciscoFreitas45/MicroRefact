package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MachineTranslationServiceController {

 private MachineTranslationService machinetranslationservice;


@PutMapping
("/saveSuccessResponse")
public void saveSuccessResponse(@RequestParam(name = "requestId") String requestId,@RequestParam(name = "targetLanguage") String targetLanguage,@RequestParam(name = "translatedText") String translatedText){
machinetranslationservice.saveSuccessResponse(requestId,targetLanguage,translatedText);
}


@PutMapping
("/saveErrorResponse")
public void saveErrorResponse(@RequestParam(name = "requestId") String requestId,@RequestParam(name = "targetLanguage") String targetLanguage,@RequestParam(name = "errorCode") String errorCode,@RequestParam(name = "errorMessage") String errorMessage){
machinetranslationservice.saveErrorResponse(requestId,targetLanguage,errorCode,errorMessage);
}


}