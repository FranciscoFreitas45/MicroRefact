package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.Client;
public class ClientImpl implements Client{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public List<String> result(String check){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/result"))
    .queryParam("check",check)
;  List<String> aux = restTemplate.getForObject(builder.toUriString(), List<String>.class);

 return aux;
}


}