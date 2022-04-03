package pl.szymanski.sharelibrary.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szymanski.sharelibrary.Interface.CategoryJPARepository;
public class CategoryJPARepositoryImpl implements CategoryJPARepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://13";


public Object findAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Optional<Category> findFirstByNameIgnoreCase(String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findFirstByNameIgnoreCase"))
    .queryParam("name",name)
;  Optional<Category> aux = restTemplate.getForObject(builder.toUriString(), Optional<Category>.class);

 return aux;
}


}