package switchtwentytwenty.project.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import switchtwentytwenty.project.Interface.IAccountRepository;
public class IAccountRepositoryImpl implements IAccountRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Account findByID(AccountID id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByID"))
    .queryParam("id",id)
;  Account aux = restTemplate.getForObject(builder.toUriString(), Account.class);

 return aux;
}


public void save(Account entity){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("entity",entity)
;
  restTemplate.put(builder.toUriString(), null);
}


}