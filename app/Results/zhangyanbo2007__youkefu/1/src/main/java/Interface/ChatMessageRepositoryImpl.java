package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.ChatMessageRepository;
public class ChatMessageRepositoryImpl implements ChatMessageRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public ChatMessage findById(String id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("id",id)
;  ChatMessage aux = restTemplate.getForObject(builder.toUriString(), ChatMessage.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Page<ChatMessage> findByAgentserviceidAndOrgi(String agentserviceid,String orgi,Pageable page){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByAgentserviceidAndOrgi"))
    .queryParam("agentserviceid",agentserviceid)
    .queryParam("orgi",orgi)
    .queryParam("page",page);
  Page<ChatMessage> aux = restTemplate.getForObject(builder.toUriString(), Page<ChatMessage>.class);

 return aux;
}


}