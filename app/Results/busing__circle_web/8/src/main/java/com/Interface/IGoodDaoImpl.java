package com.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.Interface.IGoodDao;
public class IGoodDaoImpl implements IGoodDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public void addBuyNum(String goodId,int buyNum){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addBuyNum"))
    .queryParam("goodId",goodId)
    .queryParam("buyNum",buyNum)
;
  restTemplate.put(builder.toUriString(), null);
}


public void batchAddBuyNum(List<Map<String,?>> paramListMap){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/batchAddBuyNum"))
    .queryParam("paramListMap",paramListMap)
;
  restTemplate.put(builder.toUriString(), null);
}


}