package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.DSDataEvent;
public class DSDataEventImpl implements DSDataEvent{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public DSData getDSData(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getDSData"))
;  DSData aux = restTemplate.getForObject(builder.toUriString(), DSData.class);

 return aux;
}


public String getTablename(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getTablename"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public String getOrgi(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getOrgi"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}