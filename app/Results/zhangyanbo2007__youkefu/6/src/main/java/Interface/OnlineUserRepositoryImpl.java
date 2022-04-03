package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.OnlineUserRepository;
public class OnlineUserRepositoryImpl implements OnlineUserRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public List<Object> findByOrgiAndStatus(String orgi,String status){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrgiAndStatus"))
    .queryParam("orgi",orgi)
    .queryParam("status",status)
;  List<Object> aux = restTemplate.getForObject(builder.toUriString(), List<Object>.class);

 return aux;
}


public List<Object> findByOrgiAndAgentnoAndCreatetimeRange(String orgi,String agentno,Date start,Date end){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrgiAndAgentnoAndCreatetimeRange"))
    .queryParam("orgi",orgi)
    .queryParam("agentno",agentno)
    .queryParam("start",start)
    .queryParam("end",end)
;  List<Object> aux = restTemplate.getForObject(builder.toUriString(), List<Object>.class);

 return aux;
}


public Long countByAgentForAgentUser(String orgi,String status,String agentno,Date start,Date end){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/countByAgentForAgentUser"))
    .queryParam("orgi",orgi)
    .queryParam("status",status)
    .queryParam("agentno",agentno)
    .queryParam("start",start)
    .queryParam("end",end)
;  Long aux = restTemplate.getForObject(builder.toUriString(), Long.class);

 return aux;
}


public Long countByAgentForAvagTime(String orgi,String status,String agentno,Date start,Date end){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/countByAgentForAvagTime"))
    .queryParam("orgi",orgi)
    .queryParam("status",status)
    .queryParam("agentno",agentno)
    .queryParam("start",start)
    .queryParam("end",end)
;  Long aux = restTemplate.getForObject(builder.toUriString(), Long.class);

 return aux;
}


public List<Object> findByOrgiAndCreatetimeRange(String orgi,String model,Date start,Date end){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrgiAndCreatetimeRange"))
    .queryParam("orgi",orgi)
    .queryParam("model",model)
    .queryParam("start",start)
    .queryParam("end",end)
;  List<Object> aux = restTemplate.getForObject(builder.toUriString(), List<Object>.class);

 return aux;
}


public List<Object> findByOrgiAndCreatetimeRangeForAgent(String orgi,Date start,Date end){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrgiAndCreatetimeRangeForAgent"))
    .queryParam("orgi",orgi)
    .queryParam("start",start)
    .queryParam("end",end)
;  List<Object> aux = restTemplate.getForObject(builder.toUriString(), List<Object>.class);

 return aux;
}


public List<Object> findByOrgiAndCreatetimeRangeForClient(String orgi,Date start,Date end,String channel){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrgiAndCreatetimeRangeForClient"))
    .queryParam("orgi",orgi)
    .queryParam("start",start)
    .queryParam("end",end)
    .queryParam("channel",channel)
;  List<Object> aux = restTemplate.getForObject(builder.toUriString(), List<Object>.class);

 return aux;
}


public List<Object> findByOrgiAndCreatetimeRangeForBrowser(String orgi,Date start,Date end,String channel){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrgiAndCreatetimeRangeForBrowser"))
    .queryParam("orgi",orgi)
    .queryParam("start",start)
    .queryParam("end",end)
    .queryParam("channel",channel)
;  List<Object> aux = restTemplate.getForObject(builder.toUriString(), List<Object>.class);

 return aux;
}


}