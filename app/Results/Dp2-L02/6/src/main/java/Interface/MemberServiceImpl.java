package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.MemberService;
public class MemberServiceImpl implements MemberService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Member createMember(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createMember"))
;  Member aux = restTemplate.getForObject(builder.toUriString(), Member.class);

 return aux;
}


public Member reconstruct(Member member,BindingResult binding){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/reconstruct"))
    .queryParam("member",member)
    .queryParam("binding",binding)
;  Member aux = restTemplate.getForObject(builder.toUriString(), Member.class);

 return aux;
}


public Member saveCreate(Member member){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveCreate"))
    .queryParam("member",member)
;  Member aux = restTemplate.getForObject(builder.toUriString(), Member.class);

 return aux;
}


}