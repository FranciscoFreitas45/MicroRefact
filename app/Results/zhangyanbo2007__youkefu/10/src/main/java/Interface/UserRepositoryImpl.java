package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.UserRepository;
public class UserRepositoryImpl implements UserRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Page<User> findByDatastatusAndOrgiAndOrgid(boolean b,String orgi,String orgid,Pageable pageRequest){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByDatastatusAndOrgiAndOrgid"))
    .queryParam("b",b)
    .queryParam("orgi",orgi)
    .queryParam("orgid",orgid)
    .queryParam("pageRequest",pageRequest)
;  Page<User> aux = restTemplate.getForObject(builder.toUriString(), Page<User>.class);

 return aux;
}


}