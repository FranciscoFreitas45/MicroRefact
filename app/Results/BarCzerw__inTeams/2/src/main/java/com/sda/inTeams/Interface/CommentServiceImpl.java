package com.sda.inTeams.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.sda.inTeams.Interface.CommentService;
public class CommentServiceImpl implements CommentService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public List<Comment> getAll(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAll"))
;  List<Comment> aux = restTemplate.getForObject(builder.toUriString(), List<Comment>.class);

 return aux;
}


}