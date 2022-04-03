package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.MemberService;
public class MemberServiceImpl implements MemberService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<Member> findAll(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
;  List<Member> aux = restTemplate.getForObject(builder.toUriString(), List<Member>.class);

 return aux;
}


public Object isEmpty(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isEmpty"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Member loggedMember(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/loggedMember"))
  Member aux = restTemplate.getForObject(builder.toUriString(), Member.class);

 return aux;
}


}