package com.zis.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.Interface.BookService;
public class BookServiceImpl implements BookService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public void saveShopItemForBatch(List<ShopItemInfoDTO> list){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveShopItemForBatch"))
    .queryParam("list",list)
;
  restTemplate.put(builder.toUriString(), null);
}


public void updateTitleAndCategoryForBatch(List<ShopItemInfoDTO> list){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateTitleAndCategoryForBatch"))
    .queryParam("list",list)
;
  restTemplate.put(builder.toUriString(), null);
}


}