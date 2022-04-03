package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.LogRepository;
public class LogRepositoryImpl implements LogRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public Page<Log> findByOrgi(String orgi,Pageable page){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrgi"))
    .queryParam("orgi",orgi)
    .queryParam("page",page)
;  Page<Log> aux = restTemplate.getForObject(builder.toUriString(), Page<Log>.class);

 return aux;
}


public Page<Log> findByOrgiAndLevels(String orgi,String levels,Pageable page){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrgiAndLevels"))
    .queryParam("orgi",orgi)
    .queryParam("levels",levels)
    .queryParam("page",page)
;  Page<Log> aux = restTemplate.getForObject(builder.toUriString(), Page<Log>.class);

 return aux;
}


public Object findOne(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}