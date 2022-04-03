package cn.offway.athena.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.offway.athena.Interface.PhUserInfoService;
public class PhUserInfoServiceImpl implements PhUserInfoService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public PhUserInfo findByUnionid(String unionid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUnionid"))
    .queryParam("unionid",unionid)
;  PhUserInfo aux = restTemplate.getForObject(builder.toUriString(), PhUserInfo.class);

 return aux;
}


}