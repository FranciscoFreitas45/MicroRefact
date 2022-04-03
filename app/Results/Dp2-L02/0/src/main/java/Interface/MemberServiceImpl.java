package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.MemberService;
public class MemberServiceImpl implements MemberService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Member save(Member member){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("member",member)
;  Member aux = restTemplate.getForObject(builder.toUriString(), Member.class);

 return aux;
}


public List<Member> findAll(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
;  List<Member> aux = restTemplate.getForObject(builder.toUriString(), List<Member>.class);

 return aux;
}


}