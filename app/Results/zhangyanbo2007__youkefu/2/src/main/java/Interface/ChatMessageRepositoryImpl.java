package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.ChatMessageRepository;
public class ChatMessageRepositoryImpl implements ChatMessageRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Page<ChatMessage> findByContextidAndUseridAndOrgi(String contextid,String userid,String orgi,Pageable page){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByContextidAndUseridAndOrgi"))
    .queryParam("contextid",contextid)
    .queryParam("userid",userid)
    .queryParam("orgi",orgi)
    .queryParam("page",page)
;  Page<ChatMessage> aux = restTemplate.getForObject(builder.toUriString(), Page<ChatMessage>.class);

 return aux;
}


public Page<ChatMessage> findByContextidAndOrgi(String contextid,String orgi,Pageable page){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByContextidAndOrgi"))
    .queryParam("contextid",contextid)
    .queryParam("orgi",orgi)
    .queryParam("page",page)
;  Page<ChatMessage> aux = restTemplate.getForObject(builder.toUriString(), Page<ChatMessage>.class);

 return aux;
}


}