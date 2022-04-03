package switchtwentytwenty.project.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import switchtwentytwenty.project.Interface.IPersonRepository;
public class IPersonRepositoryImpl implements IPersonRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Person findByID(Email id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByID"))
    .queryParam("id",id)
;  Person aux = restTemplate.getForObject(builder.toUriString(), Person.class);

 return aux;
}


public Person findByAccountID(AccountID accountID){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByAccountID"))
    .queryParam("accountID",accountID)
;  Person aux = restTemplate.getForObject(builder.toUriString(), Person.class);

 return aux;
}


}