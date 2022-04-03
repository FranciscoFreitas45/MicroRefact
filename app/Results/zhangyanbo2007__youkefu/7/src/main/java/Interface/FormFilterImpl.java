package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.FormFilter;
public class FormFilterImpl implements FormFilter{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public String getFiltertype(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getFiltertype"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public Object equals(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/equals"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public String getTableid(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getTableid"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public String getBatid(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getBatid"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public String getId(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getId"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public int getExecnum(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getExecnum"))
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public void setExecnum(int execnum){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setExecnum"))
    .queryParam("execnum",execnum)
;
  restTemplate.put(builder.toUriString(), null);
}


public String getName(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getName"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public int getFilternum(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getFilternum"))
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public void setFilternum(int filternum){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setFilternum"))
    .queryParam("filternum",filternum)
;
  restTemplate.put(builder.toUriString(), null);
}


}