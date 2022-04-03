package com.dtdhehe.ptulife.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.dtdhehe.ptulife.Interface.AnswerService;
public class AnswerServiceImpl implements AnswerService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public List<PtuAnswer> queryAllAnswer(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryAllAnswer"))
;  List<PtuAnswer> aux = restTemplate.getForObject(builder.toUriString(), List<PtuAnswer>.class);

 return aux;
}


}