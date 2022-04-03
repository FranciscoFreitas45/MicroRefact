package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class ViewRowAttributesLayoutImpl implements ViewRowAttributesLayout{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public ViewRowAttributesLayout of(List<DocumentLayoutElementDescriptor> elements){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))
    .queryParam("elements",elements);
  ViewRowAttributesLayout aux = restTemplate.getForObject(builder.toUriString(), ViewRowAttributesLayout.class);

 return aux;
}


}