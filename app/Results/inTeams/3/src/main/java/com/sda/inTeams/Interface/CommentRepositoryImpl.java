package com.sda.inTeams.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.sda.inTeams.Interface.CommentRepository;
public class CommentRepositoryImpl implements CommentRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Object saveAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public List<Comment> findAllByTask(Task task){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllByTask"))
    .queryParam("task",task)
;  List<Comment> aux = restTemplate.getForObject(builder.toUriString(), List<Comment>.class);

 return aux;
}


}