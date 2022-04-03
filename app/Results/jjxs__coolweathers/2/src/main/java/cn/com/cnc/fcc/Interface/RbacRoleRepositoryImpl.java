package cn.com.cnc.fcc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.cnc.fcc.Interface.RbacRoleRepository;
public class RbacRoleRepositoryImpl implements RbacRoleRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://12";


public List<RbacRole> findByRoleCode(String s){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByRoleCode"))
    .queryParam("s",s)
;  List<RbacRole> aux = restTemplate.getForObject(builder.toUriString(), List<RbacRole>.class);

 return aux;
}


}