package org.live.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.DTO.Member;
import org.live.Request.MemberRequest;
public class MemberRequestImpl implements MemberRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Member getMember(String idU3LS){
 Member aux = restTemplate.getForObject("http://5/MobileUser/{id}/Member/getMember",Member.class,idU3LS);
return aux;
}


public void setMember(Member member,String idU3LS){
 restTemplate.put("http://5/MobileUser/{id}/Member/setMember",member,idU3LS);
 return ;
}


}