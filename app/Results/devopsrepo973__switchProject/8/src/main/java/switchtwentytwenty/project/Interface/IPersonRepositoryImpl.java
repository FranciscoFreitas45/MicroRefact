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


public boolean existsByID(Email id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/existsByID"))
    .queryParam("id",id)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public void save(Person entity){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("entity",entity)
;
  restTemplate.put(builder.toUriString(), null);
}


public boolean existsByFamilyIDAndVat(FamilyID familyID,VAT vat){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/existsByFamilyIDAndVat"))
    .queryParam("familyID",familyID)
    .queryParam("vat",vat)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


}