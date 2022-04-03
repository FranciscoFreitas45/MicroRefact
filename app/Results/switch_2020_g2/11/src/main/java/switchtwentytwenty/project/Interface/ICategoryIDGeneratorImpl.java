package switchtwentytwenty.project.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import switchtwentytwenty.project.Interface.ICategoryIDGenerator;
public class ICategoryIDGeneratorImpl implements ICategoryIDGenerator{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public CategoryID generate(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/generate"))
;  CategoryID aux = restTemplate.getForObject(builder.toUriString(), CategoryID.class);

 return aux;
}


}