package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.MessageService;
public class MessageServiceImpl implements MessageService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public Message createNotification(String Subject,String body,String priority,String tags,Actor recipient){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createNotification"))
    .queryParam("Subject",Subject)
    .queryParam("body",body)
    .queryParam("priority",priority)
    .queryParam("tags",tags)
    .queryParam("recipient",recipient)
;  Message aux = restTemplate.getForObject(builder.toUriString(), Message.class);

 return aux;
}


public Message sendMessageAnotherSender(Message message){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/sendMessageAnotherSender"))
    .queryParam("message",message)
;  Message aux = restTemplate.getForObject(builder.toUriString(), Message.class);

 return aux;
}


public void updateSendedMessageByLogguedActor(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateSendedMessageByLogguedActor"))

  restTemplate.put(builder.toUriString(), null);
}


public void updateReceivedMessageToLogguedActor(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateReceivedMessageToLogguedActor"))

  restTemplate.put(builder.toUriString(), null);
}


}