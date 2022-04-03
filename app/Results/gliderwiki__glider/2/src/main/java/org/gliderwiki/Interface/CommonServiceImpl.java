package org.gliderwiki.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.gliderwiki.Interface.CommonService;
public class CommonServiceImpl implements CommonService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public WeMenu getMenuInfo(Integer we_menu_idx){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getMenuInfo"))
    .queryParam("we_menu_idx",we_menu_idx)
;  WeMenu aux = restTemplate.getForObject(builder.toUriString(), WeMenu.class);

 return aux;
}


}