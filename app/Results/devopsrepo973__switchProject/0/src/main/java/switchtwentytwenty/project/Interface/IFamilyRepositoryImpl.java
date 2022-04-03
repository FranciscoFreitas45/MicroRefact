package switchtwentytwenty.project.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import switchtwentytwenty.project.Interface.IFamilyRepository;
public class IFamilyRepositoryImpl implements IFamilyRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public Family findByID(FamilyID id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByID"))
    .queryParam("id",id)
;  Family aux = restTemplate.getForObject(builder.toUriString(), Family.class);

 return aux;
}


public FamilyJPA save(Family entity){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("entity",entity)
;  FamilyJPA aux = restTemplate.getForObject(builder.toUriString(), FamilyJPA.class);

 return aux;
}


}