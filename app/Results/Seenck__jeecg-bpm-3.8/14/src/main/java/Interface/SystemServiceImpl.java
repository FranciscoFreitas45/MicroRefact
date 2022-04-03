package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.SystemService;
public class SystemServiceImpl implements SystemService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Object getEntity(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEntity"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public void addLog(String LogContent,Short operatetype,Short loglevel){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addLog"))
    .queryParam("LogContent",LogContent)
    .queryParam("operatetype",operatetype)
    .queryParam("loglevel",loglevel)
;
  restTemplate.put(builder.toUriString(), null);
}


public Object findHql(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findHql"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}