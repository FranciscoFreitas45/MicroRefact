package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.MemberService;
public class MemberServiceImpl implements MemberService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public void deleteLoggedMember(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteLoggedMember"))
;
  restTemplate.put(builder.toUriString(), null);
}


}