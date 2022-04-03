package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.MicrosoftTranslationService;
public class MicrosoftTranslationServiceImpl implements MicrosoftTranslationService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public String[] translate(String[] sourceTexts,String sourceLanguage,String targetLangauge){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/translate"))
    .queryParam("sourceTexts",sourceTexts)
    .queryParam("sourceLanguage",sourceLanguage)
    .queryParam("targetLangauge",targetLangauge)
;  String[] aux = restTemplate.getForObject(builder.toUriString(), String[].class);

 return aux;
}


}