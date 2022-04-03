package main.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import main.Interface.TagRepository;
public class TagRepositoryImpl implements TagRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public List<String> findByName(String query){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByName"))
    .queryParam("query",query)
;  List<String> aux = restTemplate.getForObject(builder.toUriString(), List<String>.class);

 return aux;
}


public List<String> findNamesOfTags(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findNamesOfTags"))
;  List<String> aux = restTemplate.getForObject(builder.toUriString(), List<String>.class);

 return aux;
}


public Object getOne(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getOne"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}