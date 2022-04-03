package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.AdminService;
public class AdminServiceImpl implements AdminService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public Admin loggedAdmin(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/loggedAdmin"))
;  Admin aux = restTemplate.getForObject(builder.toUriString(), Admin.class);

 return aux;
}


public Admin reconstruct(Admin admin,BindingResult binding){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/reconstruct"))
    .queryParam("admin",admin)
    .queryParam("binding",binding)
;  Admin aux = restTemplate.getForObject(builder.toUriString(), Admin.class);

 return aux;
}


public Admin updateAdmin(Admin admin){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateAdmin"))
    .queryParam("admin",admin)
;  Admin aux = restTemplate.getForObject(builder.toUriString(), Admin.class);

 return aux;
}


}