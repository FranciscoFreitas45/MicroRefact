package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.MessageService;
public class MessageServiceImpl implements MessageService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public void updateSendedMessageByLogguedActor(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateSendedMessageByLogguedActor"))
;
  restTemplate.put(builder.toUriString(), null);
}


public void updateReceivedMessageToLogguedActor(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateReceivedMessageToLogguedActor"))
;
  restTemplate.put(builder.toUriString(), null);
}


public void deleteAllMessageFromActor(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteAllMessageFromActor"))
;
  restTemplate.put(builder.toUriString(), null);
}


}