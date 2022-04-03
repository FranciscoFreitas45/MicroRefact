package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class ASIRepositoryImpl implements ASIRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public ASIDocument loadReadonly(AttributeSetInstanceId attributeSetInstanceId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/loadReadonly"))
    .queryParam("attributeSetInstanceId",attributeSetInstanceId);
  ASIDocument aux = restTemplate.getForObject(builder.toUriString(), ASIDocument.class);

 return aux;
}


}