package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class WebsocketSenderImpl implements WebsocketSender{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public void convertAndSend(String destination,Object event){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/convertAndSend"))
    .queryParam("destination",destination)
    .queryParam("event",event);

  restTemplate.put(builder.toUriString(), null);
}


}