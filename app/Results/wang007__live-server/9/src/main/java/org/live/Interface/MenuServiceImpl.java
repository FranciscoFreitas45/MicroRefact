package org.live.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.Interface.MenuService;
public class MenuServiceImpl implements MenuService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public List<SidebarNode> findSidebarNodesByUserId(String userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findSidebarNodesByUserId"))
    .queryParam("userId",userId)
;  List<SidebarNode> aux = restTemplate.getForObject(builder.toUriString(), List<SidebarNode>.class);

 return aux;
}


}