package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.SessionTypeRepository;
public class SessionTypeRepositoryImpl implements SessionTypeRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public List<SessionType> findByOrgiAndCtype(String orgi,String ctype){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrgiAndCtype"))
    .queryParam("orgi",orgi)
    .queryParam("ctype",ctype)
;  List<SessionType> aux = restTemplate.getForObject(builder.toUriString(), List<SessionType>.class);

 return aux;
}


public SessionType findById(String id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("id",id)
;  SessionType aux = restTemplate.getForObject(builder.toUriString(), SessionType.class);

 return aux;
}


}