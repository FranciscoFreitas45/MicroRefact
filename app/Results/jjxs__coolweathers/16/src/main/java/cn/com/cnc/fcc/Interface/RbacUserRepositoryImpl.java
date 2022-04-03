package cn.com.cnc.fcc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.cnc.fcc.Interface.RbacUserRepository;
public class RbacUserRepositoryImpl implements RbacUserRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public List<RbacUser> findAllNonExistDefault(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllNonExistDefault"))
;  List<RbacUser> aux = restTemplate.getForObject(builder.toUriString(), List<RbacUser>.class);

 return aux;
}


}