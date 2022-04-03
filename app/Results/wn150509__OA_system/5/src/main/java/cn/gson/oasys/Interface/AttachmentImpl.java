package cn.gson.oasys.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.Interface.Attachment;
public class AttachmentImpl implements Attachment{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Long getAttachmentId(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAttachmentId"))
;  Long aux = restTemplate.getForObject(builder.toUriString(), Long.class);

 return aux;
}


public Long getAttachmentSize(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAttachmentSize"))
;  Long aux = restTemplate.getForObject(builder.toUriString(), Long.class);

 return aux;
}


public Object intValue(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/intValue"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public String getAttachmentType(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAttachmentType"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public String getAttachmentName(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAttachmentName"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public Object getBytes(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getBytes"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}