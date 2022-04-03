package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.AdminService;
public class AdminServiceImpl implements AdminService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public Admin getSystem(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSystem"))
;  Admin aux = restTemplate.getForObject(builder.toUriString(), Admin.class);

 return aux;
}


public Admin getAdminByUsername(String username){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAdminByUsername"))
    .queryParam("username",username)
;  Admin aux = restTemplate.getForObject(builder.toUriString(), Admin.class);

 return aux;
}


public void banSuspiciousActor(Actor a){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/banSuspiciousActor"))
    .queryParam("a",a);

  restTemplate.put(builder.toUriString(), null);
}


public void unBanSuspiciousActor(Actor a){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/unBanSuspiciousActor"))
    .queryParam("a",a);

  restTemplate.put(builder.toUriString(), null);
}


public Admin reconstruct(Admin admin,BindingResult binding){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/reconstruct"))
    .queryParam("admin",admin)
    .queryParam("binding",binding);
  Admin aux = restTemplate.getForObject(builder.toUriString(), Admin.class);

 return aux;
}


public void broadcastMessage(Message message){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/broadcastMessage"))
    .queryParam("message",message);

  restTemplate.put(builder.toUriString(), null);
}


}