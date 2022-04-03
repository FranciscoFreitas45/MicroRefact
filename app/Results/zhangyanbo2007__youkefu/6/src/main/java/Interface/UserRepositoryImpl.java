package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.UserRepository;
public class UserRepositoryImpl implements UserRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public List<User> findByOrgiAndAgentAndDatastatus(String orgi,boolean agent,boolean status){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrgiAndAgentAndDatastatus"))
    .queryParam("orgi",orgi)
    .queryParam("agent",agent)
    .queryParam("status",status)
;  List<User> aux = restTemplate.getForObject(builder.toUriString(), List<User>.class);

 return aux;
}


public List<User> findByOrgidAndAgentAndDatastatus(String orgid,boolean agent,boolean datastatus){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrgidAndAgentAndDatastatus"))
    .queryParam("orgid",orgid)
    .queryParam("agent",agent)
    .queryParam("datastatus",datastatus)
;  List<User> aux = restTemplate.getForObject(builder.toUriString(), List<User>.class);

 return aux;
}


}