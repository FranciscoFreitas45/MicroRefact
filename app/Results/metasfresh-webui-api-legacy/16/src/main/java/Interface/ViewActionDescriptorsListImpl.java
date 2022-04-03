package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class ViewActionDescriptorsListImpl implements ViewActionDescriptorsList{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public ViewActionDescriptorsList mergeWith(ViewActionDescriptorsList actionsToAdd){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/mergeWith"))
    .queryParam("actionsToAdd",actionsToAdd);
  ViewActionDescriptorsList aux = restTemplate.getForObject(builder.toUriString(), ViewActionDescriptorsList.class);

 return aux;
}


}