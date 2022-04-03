package app.qienuren.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import app.qienuren.Interface.RandomPasswordGenerator;
public class RandomPasswordGeneratorImpl implements RandomPasswordGenerator{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public String generatePassayPassword(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/generatePassayPassword"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}