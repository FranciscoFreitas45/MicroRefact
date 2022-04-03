package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class DataEntrySubTabBindingDescriptorBuilderImpl implements DataEntrySubTabBindingDescriptorBuilder{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Object getDataEntryWebuiTools(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getDataEntryWebuiTools"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}