package run.halo.app.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import run.halo.app.Interface.CategoryService;
public class CategoryServiceImpl implements CategoryService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<Category> listAll(Sort sort,boolean queryEncryptCategory){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/listAll"))
    .queryParam("sort",sort)
    .queryParam("queryEncryptCategory",queryEncryptCategory)
;  List<Category> aux = restTemplate.getForObject(builder.toUriString(), List<Category>.class);

 return aux;
}


public Object createInBatch(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createInBatch"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}