package cn.offway.athena.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.offway.athena.Interface.PhRoleadminRepository;
public class PhRoleadminRepositoryImpl implements PhRoleadminRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://12";


public int deleteByRoleIds(List<Long> ids){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteByRoleIds"))
    .queryParam("ids",ids)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}