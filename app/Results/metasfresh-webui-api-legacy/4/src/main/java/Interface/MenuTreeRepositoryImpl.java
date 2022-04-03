package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class MenuTreeRepositoryImpl implements MenuTreeRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://12";


public MenuTree getMenuTree(UserRolePermissionsKey userRolePermissionsKey,String adLanguage){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getMenuTree"))
    .queryParam("userRolePermissionsKey",userRolePermissionsKey)
    .queryParam("adLanguage",adLanguage);
  MenuTree aux = restTemplate.getForObject(builder.toUriString(), MenuTree.class);

 return aux;
}


}