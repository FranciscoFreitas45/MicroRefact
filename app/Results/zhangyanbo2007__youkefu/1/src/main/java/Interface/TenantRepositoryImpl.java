package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.TenantRepository;
public class TenantRepositoryImpl implements TenantRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public Tenant findById(String id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("id",id)
;  Tenant aux = restTemplate.getForObject(builder.toUriString(), Tenant.class);

 return aux;
}


}