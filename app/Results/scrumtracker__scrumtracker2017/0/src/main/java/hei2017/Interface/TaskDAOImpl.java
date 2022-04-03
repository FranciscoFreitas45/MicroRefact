package hei2017.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import hei2017.Interface.TaskDAO;
public class TaskDAOImpl implements TaskDAO{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public Object findAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Set<Task> findByTaskStoryId(Long idStory){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByTaskStoryId"))
    .queryParam("idStory",idStory)
;  Set<Task> aux = restTemplate.getForObject(builder.toUriString(), Set<Task>.class);

 return aux;
}


public Object findOne(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Task findOneById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOneById"))
    .queryParam("id",id)
;  Task aux = restTemplate.getForObject(builder.toUriString(), Task.class);

 return aux;
}


public Task findOneByNom(String nom){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOneByNom"))
    .queryParam("nom",nom)
;  Task aux = restTemplate.getForObject(builder.toUriString(), Task.class);

 return aux;
}


public long count(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/count"))
;  long aux = restTemplate.getForObject(builder.toUriString(), long.class);

 return aux;
}


public Object delete(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object exists(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/exists"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}