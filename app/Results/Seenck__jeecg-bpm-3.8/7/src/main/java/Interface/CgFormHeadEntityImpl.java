package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.CgFormHeadEntity;
public class CgFormHeadEntityImpl implements CgFormHeadEntity{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public String getSubTableStr(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSubTableStr"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public Object length(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/length"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object split(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/split"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public java.lang.String getContent(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getContent"))
;  java.lang.String aux = restTemplate.getForObject(builder.toUriString(), java.lang.String.class);

 return aux;
}


public java.lang.String getTableName(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getTableName"))
;  java.lang.String aux = restTemplate.getForObject(builder.toUriString(), java.lang.String.class);

 return aux;
}


public List<CgFormFieldEntity> getColumns(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getColumns"))
;  List<CgFormFieldEntity> aux = restTemplate.getForObject(builder.toUriString(), List<CgFormFieldEntity>.class);

 return aux;
}


}