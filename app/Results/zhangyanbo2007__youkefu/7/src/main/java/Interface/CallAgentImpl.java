package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.CallAgent;
public class CallAgentImpl implements CallAgent{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public int getDisnum(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getDisnum"))
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public AtomicInteger getDisnames(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getDisnames"))
;  AtomicInteger aux = restTemplate.getForObject(builder.toUriString(), AtomicInteger.class);

 return aux;
}


public Object incrementAndGet(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/incrementAndGet"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public String getDistype(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getDistype"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public String getDistarget(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getDistarget"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public String getOrgan(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getOrgan"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public Object intValue(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/intValue"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}