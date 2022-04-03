package com.easyshopping.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.easyshopping.Interface.MemberRankDao;
public class MemberRankDaoImpl implements MemberRankDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public MemberRank findByAmount(BigDecimal amount){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByAmount"))
    .queryParam("amount",amount)
;  MemberRank aux = restTemplate.getForObject(builder.toUriString(), MemberRank.class);

 return aux;
}


}