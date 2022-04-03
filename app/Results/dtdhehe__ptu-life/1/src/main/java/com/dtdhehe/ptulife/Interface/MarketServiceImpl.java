package com.dtdhehe.ptulife.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.dtdhehe.ptulife.Interface.MarketService;
public class MarketServiceImpl implements MarketService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public Page<Market> queryGoodsById(String id,String goodsName,Pageable pageable){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryGoodsById"))
    .queryParam("id",id)
    .queryParam("goodsName",goodsName)
    .queryParam("pageable",pageable)
;  Page<Market> aux = restTemplate.getForObject(builder.toUriString(), Page<Market>.class);

 return aux;
}


public void delGoodsById(String id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delGoodsById"))
    .queryParam("id",id)
;
  restTemplate.put(builder.toUriString(), null);
}


}