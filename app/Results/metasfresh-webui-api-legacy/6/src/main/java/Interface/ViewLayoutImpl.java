package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class ViewLayoutImpl implements ViewLayout{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public ImmutableList<DocumentFilterDescriptor> getFilters(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getFilters"))
  ImmutableList<DocumentFilterDescriptor> aux = restTemplate.getForObject(builder.toUriString(), ImmutableList<DocumentFilterDescriptor>.class);

 return aux;
}


}