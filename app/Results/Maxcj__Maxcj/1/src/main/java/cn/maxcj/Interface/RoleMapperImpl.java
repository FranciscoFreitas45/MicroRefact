package cn.maxcj.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.maxcj.Interface.RoleMapper;
public class RoleMapperImpl implements RoleMapper{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Object selectById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/selectById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}