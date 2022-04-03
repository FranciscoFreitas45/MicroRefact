package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class LookupValueByIdSupplierImpl implements LookupValueByIdSupplier{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public LookupValue findById(Object id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("id",id);
  LookupValue aux = restTemplate.getForObject(builder.toUriString(), LookupValue.class);

 return aux;
}


}