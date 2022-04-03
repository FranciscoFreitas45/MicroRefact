package com.zis.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.Interface.DoPurchaseService;
public class DoPurchaseServiceImpl implements DoPurchaseService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public String addPurchaseDetail(int bookId,int purchasedAmount,String operator,String position,String memo,Integer repoId,Integer stockAmount){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addPurchaseDetail"))
    .queryParam("bookId",bookId)
    .queryParam("purchasedAmount",purchasedAmount)
    .queryParam("operator",operator)
    .queryParam("position",position)
    .queryParam("memo",memo)
    .queryParam("repoId",repoId)
    .queryParam("stockAmount",stockAmount)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}