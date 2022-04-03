package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class GeoLocationDocumentDescriptorImpl implements GeoLocationDocumentDescriptor{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public Object builder(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/builder"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object type(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/type"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object locationColumnName(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/locationColumnName"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object getLocationColumnName(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getLocationColumnName"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}