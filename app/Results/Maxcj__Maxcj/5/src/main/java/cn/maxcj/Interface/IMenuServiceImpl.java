package cn.maxcj.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.maxcj.Interface.IMenuService;
public class IMenuServiceImpl implements IMenuService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public List<MenuNode> getMenusByRoleIds(List<Integer> roleIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getMenusByRoleIds"))
    .queryParam("roleIds",roleIds)
;  List<MenuNode> aux = restTemplate.getForObject(builder.toUriString(), List<MenuNode>.class);

 return aux;
}


}