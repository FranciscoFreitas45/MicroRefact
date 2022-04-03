package goorum.goorum.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import goorum.goorum.Interface.MemberService;
public class MemberServiceImpl implements MemberService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public boolean setProfilePhoto(long memberId,String profilePath){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setProfilePhoto"))
    .queryParam("memberId",memberId)
    .queryParam("profilePath",profilePath)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


}