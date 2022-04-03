package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class GeoLocationDocumentServiceImpl implements GeoLocationDocumentService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://16";


public boolean hasGeoLocationSupport(Set<String> fieldNames){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/hasGeoLocationSupport"))
    .queryParam("fieldNames",fieldNames);
  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


}