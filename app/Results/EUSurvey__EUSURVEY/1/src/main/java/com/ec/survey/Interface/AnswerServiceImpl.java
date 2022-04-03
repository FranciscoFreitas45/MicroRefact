package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.AnswerService;
public class AnswerServiceImpl implements AnswerService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public String serializeOriginal(Integer id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/serializeOriginal"))
    .queryParam("id",id)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public void internalSaveAnswerSet(AnswerSet answerSet,String fileDir,String draftid,boolean invalidateExportsAndStatistics,boolean createAttendees){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/internalSaveAnswerSet"))
    .queryParam("answerSet",answerSet)
    .queryParam("fileDir",fileDir)
    .queryParam("draftid",draftid)
    .queryParam("invalidateExportsAndStatistics",invalidateExportsAndStatistics)
    .queryParam("createAttendees",createAttendees)
;
  restTemplate.put(builder.toUriString(), null);
}


public int getNumberOfAnswerSetsPublished(String surveyname,String uid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getNumberOfAnswerSetsPublished"))
    .queryParam("surveyname",surveyname)
    .queryParam("uid",uid)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}