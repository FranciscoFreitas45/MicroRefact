package cn.gson.oasys.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.Interface.MailServices;
public class MailServicesImpl implements MailServices{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Attachment upload(MultipartFile file,User mu){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/upload"))
    .queryParam("file",file)
    .queryParam("mu",mu)
;  Attachment aux = restTemplate.getForObject(builder.toUriString(), Attachment.class);

 return aux;
}


}