package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.ClientManager;
public class ClientManagerImpl implements ClientManager{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Collection<Client> getAllClient(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAllClient"))
;  Collection<Client> aux = restTemplate.getForObject(builder.toUriString(), Collection<Client>.class);

 return aux;
}


}