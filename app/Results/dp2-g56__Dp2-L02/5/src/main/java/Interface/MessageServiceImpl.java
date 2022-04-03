package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.MessageService;
public class MessageServiceImpl implements MessageService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public void updateReceivedMessageToLogguedActor(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateReceivedMessageToLogguedActor"))
;
  restTemplate.put(builder.toUriString(), null);
}


public void updateSendedMessageByLogguedActor(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateSendedMessageByLogguedActor"))
;
  restTemplate.put(builder.toUriString(), null);
}


public void sendNotificationDropOut(Brotherhood bro){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/sendNotificationDropOut"))
    .queryParam("bro",bro);

  restTemplate.put(builder.toUriString(), null);
}


public void sendNotificationBroEnrolMem(Member mem){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/sendNotificationBroEnrolMem"))
    .queryParam("mem",mem);

  restTemplate.put(builder.toUriString(), null);
}


}