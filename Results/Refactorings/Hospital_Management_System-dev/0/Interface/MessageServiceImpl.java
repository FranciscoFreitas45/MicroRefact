import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class MessageServiceImpl implements MessageService{

 private RestTemplate restTemplate;

  String url = "http://5";


public void save(Message message){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("message",message);

  restTemplate.put(builder.toUriString(), null)



}