package pl.szymanski.sharelibrary.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szymanski.sharelibrary.Interface.CategoryRepository;
public class CategoryRepositoryImpl implements CategoryRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Optional<Category> findByName(String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByName"))
    .queryParam("name",name)
;  Optional<Category> aux = restTemplate.getForObject(builder.toUriString(), Optional<Category>.class);

 return aux;
}


}