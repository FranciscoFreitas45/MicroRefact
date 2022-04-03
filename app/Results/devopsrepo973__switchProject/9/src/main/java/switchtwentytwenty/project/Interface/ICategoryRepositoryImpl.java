package switchtwentytwenty.project.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import switchtwentytwenty.project.Interface.ICategoryRepository;
public class ICategoryRepositoryImpl implements ICategoryRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Category findByID(CategoryID id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByID"))
    .queryParam("id",id)
;  Category aux = restTemplate.getForObject(builder.toUriString(), Category.class);

 return aux;
}


}