package cn.com.cnc.fcc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.cnc.fcc.Interface.RbacUserRightRelationRepository;
public class RbacUserRightRelationRepositoryImpl implements RbacUserRightRelationRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public Optional<RbacUserRightRelation> findByUserId(Integer userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUserId"))
    .queryParam("userId",userId)
;  Optional<RbacUserRightRelation> aux = restTemplate.getForObject(builder.toUriString(), Optional<RbacUserRightRelation>.class);

 return aux;
}


}