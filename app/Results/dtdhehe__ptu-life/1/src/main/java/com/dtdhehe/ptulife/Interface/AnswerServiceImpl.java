package com.dtdhehe.ptulife.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.dtdhehe.ptulife.Interface.AnswerService;
public class AnswerServiceImpl implements AnswerService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public Page<PtuAnswer> queryAnswerByUserId(String userId,String answerTitle,Pageable pageable){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryAnswerByUserId"))
    .queryParam("userId",userId)
    .queryParam("answerTitle",answerTitle)
    .queryParam("pageable",pageable)
;  Page<PtuAnswer> aux = restTemplate.getForObject(builder.toUriString(), Page<PtuAnswer>.class);

 return aux;
}


public void delAnswerById(String answerId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delAnswerById"))
    .queryParam("answerId",answerId)
;
  restTemplate.put(builder.toUriString(), null);
}


}