package pl.szymanski.sharelibrary.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szymanski.sharelibrary.Interface.CategoryService;
public class CategoryServiceImpl implements CategoryService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Category findByName(String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByName"))
    .queryParam("name",name)
;  Category aux = restTemplate.getForObject(builder.toUriString(), Category.class);

 return aux;
}


}