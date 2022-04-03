package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.UserEventRepository;
public class UserEventRepositoryImpl implements UserEventRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public List<Object> findByOrgiAndCreatetimeRange(String orgi,Date start,Date end){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrgiAndCreatetimeRange"))
    .queryParam("orgi",orgi)
    .queryParam("start",start)
    .queryParam("end",end)
;  List<Object> aux = restTemplate.getForObject(builder.toUriString(), List<Object>.class);

 return aux;
}


public Page<UserHistory> findBySessionidAndOrgi(String sessionid,String orgi,Pageable page){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findBySessionidAndOrgi"))
    .queryParam("sessionid",sessionid)
    .queryParam("orgi",orgi)
    .queryParam("page",page);
  Page<UserHistory> aux = restTemplate.getForObject(builder.toUriString(), Page<UserHistory>.class);

 return aux;
}


}