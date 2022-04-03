package cn.gson.oasys.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.Interface.MailreciverDao;
public class MailreciverDaoImpl implements MailreciverDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<Mailreciver> findByReadAndDelAndReciverId(Boolean read,Boolean del,User reciverid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByReadAndDelAndReciverId"))
    .queryParam("read",read)
    .queryParam("del",del)
    .queryParam("reciverid",reciverid)
;  List<Mailreciver> aux = restTemplate.getForObject(builder.toUriString(), List<Mailreciver>.class);

 return aux;
}


}