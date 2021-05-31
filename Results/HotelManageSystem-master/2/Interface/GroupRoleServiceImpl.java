import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class GroupRoleServiceImpl implements GroupRoleService{

 private RestTemplate restTemplate;

  String url = "http://5";


public GroupRole findByGroupName(String groupName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByGroupName"))
    .queryParam("groupName",groupName);
  GroupRole aux = restTemplate.getForObject(builder.toUriString(), GroupRole.class)

 return aux;
}


}