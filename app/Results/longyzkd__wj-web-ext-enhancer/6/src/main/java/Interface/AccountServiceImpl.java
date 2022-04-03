package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.AccountService;
public class AccountServiceImpl implements AccountService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<SysOrgMenu> findMenusBy(String orgId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findMenusBy"))
    .queryParam("orgId",orgId)
;  List<SysOrgMenu> aux = restTemplate.getForObject(builder.toUriString(), List<SysOrgMenu>.class);

 return aux;
}


public List<Menu> findAllMenus(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllMenus"))
;  List<Menu> aux = restTemplate.getForObject(builder.toUriString(), List<Menu>.class);

 return aux;
}


public Object list(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/list"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}