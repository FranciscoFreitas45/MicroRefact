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


}