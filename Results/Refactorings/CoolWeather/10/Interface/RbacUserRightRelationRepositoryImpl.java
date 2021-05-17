import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class RbacUserRightRelationRepositoryImpl implements RbacUserRightRelationRepository{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public List<RbacUserRightRelation> findByRoleId(Integer id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByRoleId"))
    .queryParam("id",id);
  List<RbacUserRightRelation> aux = restTemplate.getForObject(builder.toUriString(), List<RbacUserRightRelation>.class)

 return aux;
}


}