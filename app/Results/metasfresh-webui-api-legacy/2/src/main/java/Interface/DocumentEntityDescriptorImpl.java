package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DocumentEntityDescriptorImpl implements DocumentEntityDescriptor{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Map<String,DocumentFieldDescriptor> getFields(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getFields"))
  Map<String,DocumentFieldDescriptor> aux = restTemplate.getForObject(builder.toUriString(), Map<String,DocumentFieldDescriptor>.class);

 return aux;
}


public Object isEmpty(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isEmpty"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}