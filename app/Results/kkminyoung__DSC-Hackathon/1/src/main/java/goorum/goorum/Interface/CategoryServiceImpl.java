package goorum.goorum.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import goorum.goorum.Interface.CategoryService;
public class CategoryServiceImpl implements CategoryService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public List<Category> getList(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getList"))
;  List<Category> aux = restTemplate.getForObject(builder.toUriString(), List<Category>.class);

 return aux;
}


}