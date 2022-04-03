package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MicrosoftTranslationServiceController {

 private MicrosoftTranslationService microsofttranslationservice;


@GetMapping
("/translate")
public String[] translate(@RequestParam(name = "sourceTexts") String[] sourceTexts,@RequestParam(name = "sourceLanguage") String sourceLanguage,@RequestParam(name = "targetLangauge") String targetLangauge){
  return microsofttranslationservice.translate(sourceTexts,sourceLanguage,targetLangauge);
}


}