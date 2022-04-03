package com.lingxiang2014.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.lingxiang2014.Interface.MemberRankService;
public class MemberRankServiceImpl implements MemberRankService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public MemberRank findDefault(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findDefault"))
;  MemberRank aux = restTemplate.getForObject(builder.toUriString(), MemberRank.class);

 return aux;
}


}