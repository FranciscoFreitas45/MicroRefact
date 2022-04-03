package com.hmm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.Interface.IStockService;
public class IStockServiceImpl implements IStockService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public List<DailyNecessaryDto> findByStockType(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByStockType"))
;  List<DailyNecessaryDto> aux = restTemplate.getForObject(builder.toUriString(), List<DailyNecessaryDto>.class);

 return aux;
}


}