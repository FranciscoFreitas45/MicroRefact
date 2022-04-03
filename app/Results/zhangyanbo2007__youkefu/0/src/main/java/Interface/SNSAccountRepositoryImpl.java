package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.SNSAccountRepository;
public class SNSAccountRepositoryImpl implements SNSAccountRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public SNSAccount findBySnsid(String snsid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findBySnsid"))
    .queryParam("snsid",snsid)
;  SNSAccount aux = restTemplate.getForObject(builder.toUriString(), SNSAccount.class);

 return aux;
}


public SNSAccount findBySnsidAndOrgi(String snsid,String orgi){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findBySnsidAndOrgi"))
    .queryParam("snsid",snsid)
    .queryParam("orgi",orgi);
  SNSAccount aux = restTemplate.getForObject(builder.toUriString(), SNSAccount.class);

 return aux;
}


}