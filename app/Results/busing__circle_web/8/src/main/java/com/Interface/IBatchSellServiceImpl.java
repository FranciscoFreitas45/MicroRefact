package com.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.Interface.IBatchSellService;
public class IBatchSellServiceImpl implements IBatchSellService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public BatchSell queryCurrentBatchSell(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryCurrentBatchSell"))
;  BatchSell aux = restTemplate.getForObject(builder.toUriString(), BatchSell.class);

 return aux;
}


public String queryCurrentSellGoodsId(int batchId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryCurrentSellGoodsId"))
    .queryParam("batchId",batchId)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}