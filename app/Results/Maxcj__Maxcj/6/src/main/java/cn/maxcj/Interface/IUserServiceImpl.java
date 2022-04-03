package cn.maxcj.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.maxcj.Interface.IUserService;
public class IUserServiceImpl implements IUserService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public User getByAccount(String account){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getByAccount"))
    .queryParam("account",account)
;  User aux = restTemplate.getForObject(builder.toUriString(), User.class);

 return aux;
}


public Integer isSheLian(Integer userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isSheLian"))
    .queryParam("userId",userId)
;  Integer aux = restTemplate.getForObject(builder.toUriString(), Integer.class);

 return aux;
}


}