package com.zis.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.Interface.ShopService;
public class ShopServiceImpl implements ShopService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public List<Company> queryAllCompany(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryAllCompany"))
;  List<Company> aux = restTemplate.getForObject(builder.toUriString(), List<Company>.class);

 return aux;
}


public void stockChangeToShopUPdateItem(Integer companyId,Integer bookId,Integer amount){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/stockChangeToShopUPdateItem"))
    .queryParam("companyId",companyId)
    .queryParam("bookId",bookId)
    .queryParam("amount",amount)
;
  restTemplate.put(builder.toUriString(), null);
}


}