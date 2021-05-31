import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class PhUserInfoServiceImpl implements PhUserInfoService{

 private RestTemplate restTemplate;

  String url = "http://14";


public PhUserInfo findByUnionid(String unionid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUnionid"))
    .queryParam("unionid",unionid);
  PhUserInfo aux = restTemplate.getForObject(builder.toUriString(), PhUserInfo.class)

 return aux;
}


}