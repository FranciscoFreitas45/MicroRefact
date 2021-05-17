import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class RbacUserRightRelationRepositoryImpl implements RbacUserRightRelationRepository{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public Optional<RbacUserRightRelation> findByUserId(Integer userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByUserId"))
    .queryParam("userId",userId);
  Optional<RbacUserRightRelation> aux = restTemplate.getForObject(builder.toUriString(), Optional<RbacUserRightRelation>.class)

 return aux;
}


}