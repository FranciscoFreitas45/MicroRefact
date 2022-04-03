package cn.maxcj.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.maxcj.Interface.MenuMapper;
public class MenuMapperImpl implements MenuMapper{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public List<String> getResUrlsByRoleId(Integer roleId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getResUrlsByRoleId"))
    .queryParam("roleId",roleId)
;  List<String> aux = restTemplate.getForObject(builder.toUriString(), List<String>.class);

 return aux;
}


}