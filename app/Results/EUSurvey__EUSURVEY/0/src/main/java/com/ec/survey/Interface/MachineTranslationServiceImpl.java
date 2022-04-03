package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.MachineTranslationService;
public class MachineTranslationServiceImpl implements MachineTranslationService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public void saveSuccessResponse(String requestId,String targetLanguage,String translatedText){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveSuccessResponse"))
    .queryParam("requestId",requestId)
    .queryParam("targetLanguage",targetLanguage)
    .queryParam("translatedText",translatedText)
;
  restTemplate.put(builder.toUriString(), null);
}


public void saveErrorResponse(String requestId,String targetLanguage,String errorCode,String errorMessage){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveErrorResponse"))
    .queryParam("requestId",requestId)
    .queryParam("targetLanguage",targetLanguage)
    .queryParam("errorCode",errorCode)
    .queryParam("errorMessage",errorMessage)
;
  restTemplate.put(builder.toUriString(), null);
}


}