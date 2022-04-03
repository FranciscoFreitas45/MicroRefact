package cn.com.cnc.fcc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.cnc.fcc.Interface.RbacUserRepository;
public class RbacUserRepositoryImpl implements RbacUserRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public int deleteUserNonExistDefault(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteUserNonExistDefault"))
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public Object saveAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}