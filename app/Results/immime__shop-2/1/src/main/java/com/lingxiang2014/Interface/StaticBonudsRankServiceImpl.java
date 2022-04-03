package com.lingxiang2014.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.lingxiang2014.Interface.StaticBonudsRankService;
public class StaticBonudsRankServiceImpl implements StaticBonudsRankService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public StaticBonudsRank findDefault(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findDefault"))
;  StaticBonudsRank aux = restTemplate.getForObject(builder.toUriString(), StaticBonudsRank.class);

 return aux;
}


}