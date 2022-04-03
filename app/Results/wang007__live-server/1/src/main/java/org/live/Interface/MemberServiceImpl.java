package org.live.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.Interface.MemberService;
public class MemberServiceImpl implements MemberService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public Member findMemberBymemberNo(String memberNo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findMemberBymemberNo"))
    .queryParam("memberNo",memberNo)
;  Member aux = restTemplate.getForObject(builder.toUriString(), Member.class);

 return aux;
}


}