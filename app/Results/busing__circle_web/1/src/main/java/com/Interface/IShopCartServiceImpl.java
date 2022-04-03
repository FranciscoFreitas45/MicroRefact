package com.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.Interface.IShopCartService;
public class IShopCartServiceImpl implements IShopCartService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public List<Map<String,Object>> queryShopCartList(Page page,int userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryShopCartList"))
    .queryParam("page",page)
    .queryParam("userId",userId)
;  List<Map<String,Object>> aux = restTemplate.getForObject(builder.toUriString(), List<Map<String,Object>>.class);

 return aux;
}


}