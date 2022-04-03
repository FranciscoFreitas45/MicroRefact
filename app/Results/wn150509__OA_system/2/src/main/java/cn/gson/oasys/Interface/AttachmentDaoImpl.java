package cn.gson.oasys.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.Interface.AttachmentDao;
public class AttachmentDaoImpl implements AttachmentDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Attachment findByAttachmentPath(String AttachmentPath){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByAttachmentPath"))
    .queryParam("AttachmentPath",AttachmentPath)
;  Attachment aux = restTemplate.getForObject(builder.toUriString(), Attachment.class);

 return aux;
}


}