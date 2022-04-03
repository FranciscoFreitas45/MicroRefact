package com.sda.inTeams.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.sda.inTeams.Interface.CommentRepository;
import com.sda.inTeams.DTO.*;
import java.util.*;
public class CommentRepositoryImpl implements CommentRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public List<Comment> findAllByCreator(User user){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllByCreator"))
    .queryParam("user",user)
;  List<Comment> aux = restTemplate.getForObject(builder.toUriString(), List.class);

 return aux;
}


}