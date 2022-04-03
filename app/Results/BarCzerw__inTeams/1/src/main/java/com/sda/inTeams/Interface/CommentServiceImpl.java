package com.sda.inTeams.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.sda.inTeams.Interface.CommentService;
public class CommentServiceImpl implements CommentService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public List<Comment> getAllUserComments(long userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAllUserComments"))
    .queryParam("userId",userId)
;  List<Comment> aux = restTemplate.getForObject(builder.toUriString(), List<Comment>.class);

 return aux;
}


}