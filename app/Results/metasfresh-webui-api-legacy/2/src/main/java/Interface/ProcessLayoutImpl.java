package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class ProcessLayoutImpl implements ProcessLayout{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public ITranslatableString getDescription(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getDescription"))
  ITranslatableString aux = restTemplate.getForObject(builder.toUriString(), ITranslatableString.class);

 return aux;
}


}