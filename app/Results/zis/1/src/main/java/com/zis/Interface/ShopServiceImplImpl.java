package com.zis.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.Interface.ShopServiceImpl;
public class ShopServiceImplImpl implements ShopServiceImpl{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public List<ShopInfo> findCompanyShop(Integer companyId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findCompanyShop"))
    .queryParam("companyId",companyId)
;  List<ShopInfo> aux = restTemplate.getForObject(builder.toUriString(), List<ShopInfo>.class);

 return aux;
}


}