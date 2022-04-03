package sn.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import sn.Interface.PostRepository;
public class PostRepositoryImpl implements PostRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public Object findById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public List<Post> findAllByPersonId(long personId,Pageable pageable){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllByPersonId"))
    .queryParam("personId",personId)
    .queryParam("pageable",pageable)
;  List<Post> aux = restTemplate.getForObject(builder.toUriString(), List<Post>.class);

 return aux;
}


public Object saveAndFlush(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveAndFlush"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public int getTotalCountPostsByPersonId(long personId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getTotalCountPostsByPersonId"))
    .queryParam("personId",personId)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public List<Post> findAllByTextAndTime(String text,LocalDateTime dateFrom,LocalDateTime dateTo,Pageable pageable){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllByTextAndTime"))
    .queryParam("text",text)
    .queryParam("dateFrom",dateFrom)
    .queryParam("dateTo",dateTo)
    .queryParam("pageable",pageable)
;  List<Post> aux = restTemplate.getForObject(builder.toUriString(), List<Post>.class);

 return aux;
}


public Object getOne(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getOne"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}