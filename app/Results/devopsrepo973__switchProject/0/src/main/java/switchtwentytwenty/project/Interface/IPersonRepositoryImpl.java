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


public void save(Person entity){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("entity",entity)
;
  restTemplate.put(builder.toUriString(), null);
}


public PersonList findByFamilyID(FamilyID familyID){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByFamilyID"))
    .queryParam("familyID",familyID)
;  PersonList aux = restTemplate.getForObject(builder.toUriString(), PersonList.class);

 return aux;
}


}