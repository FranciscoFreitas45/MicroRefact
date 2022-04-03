package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.Survey;
public class SurveyImpl implements Survey{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public void computeTriggers(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/computeTriggers"))
;
  restTemplate.put(builder.toUriString(), null);
}


public Language getLanguage(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getLanguage"))
;  Language aux = restTemplate.getForObject(builder.toUriString(), Language.class);

 return aux;
}


public Map<Integer,Question> getQuestionMap(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getQuestionMap"))
;  Map<Integer,Question> aux = restTemplate.getForObject(builder.toUriString(), Map<Integer,Question>.class);

 return aux;
}


public Object get(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/get"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Map<Integer,Element> getMatrixMap(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getMatrixMap"))
;  Map<Integer,Element> aux = restTemplate.getForObject(builder.toUriString(), Map<Integer,Element>.class);

 return aux;
}


public Map<String,Element> getQuestionMapByUniqueId(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getQuestionMapByUniqueId"))
;  Map<String,Element> aux = restTemplate.getForObject(builder.toUriString(), Map<String,Element>.class);

 return aux;
}


public List<Element> getElements(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getElements"))
;  List<Element> aux = restTemplate.getForObject(builder.toUriString(), List<Element>.class);

 return aux;
}


public Map<Integer,Element> getMissingElementsById(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getMissingElementsById"))
;  Map<Integer,Element> aux = restTemplate.getForObject(builder.toUriString(), Map<Integer,Element>.class);

 return aux;
}


public Object containsKey(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/containsKey"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public String getShortname(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getShortname"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public boolean getMultiPaging(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getMultiPaging"))
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public int getSectionNumbering(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSectionNumbering"))
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public int getQuestionNumbering(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getQuestionNumbering"))
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public Boolean getWcagCompliance(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getWcagCompliance"))
;  Boolean aux = restTemplate.getForObject(builder.toUriString(), Boolean.class);

 return aux;
}


}