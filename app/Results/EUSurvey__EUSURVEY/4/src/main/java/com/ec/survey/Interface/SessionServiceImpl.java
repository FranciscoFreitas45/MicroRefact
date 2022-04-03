package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.SessionService;
public class SessionServiceImpl implements SessionService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public void ClearUniqueCodeForForm(HttpServletRequest request,int surveyId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/ClearUniqueCodeForForm"))
    .queryParam("request",request)
    .queryParam("surveyId",surveyId)
;
  restTemplate.put(builder.toUriString(), null);
}


public void initializeProxy(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/initializeProxy"))
;
  restTemplate.put(builder.toUriString(), null);
}


public String getCaptchaText(HttpServletRequest request){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getCaptchaText"))
    .queryParam("request",request)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}