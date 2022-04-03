package com.dtdhehe.ptulife.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.dtdhehe.ptulife.Interface.CommentService;
public class CommentServiceImpl implements CommentService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Integer getHotByComment(String pid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getHotByComment"))
    .queryParam("pid",pid)
;  Integer aux = restTemplate.getForObject(builder.toUriString(), Integer.class);

 return aux;
}


}