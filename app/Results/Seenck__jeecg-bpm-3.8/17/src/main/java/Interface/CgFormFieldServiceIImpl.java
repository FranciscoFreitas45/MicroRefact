package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.CgFormFieldServiceI;
public class CgFormFieldServiceIImpl implements CgFormFieldServiceI{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public Object findByProperty(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByProperty"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}