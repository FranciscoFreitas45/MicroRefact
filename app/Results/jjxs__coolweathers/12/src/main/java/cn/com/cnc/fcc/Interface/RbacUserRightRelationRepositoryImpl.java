package cn.com.cnc.fcc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.cnc.fcc.Interface.RbacUserRightRelationRepository;
public class RbacUserRightRelationRepositoryImpl implements RbacUserRightRelationRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public List<RbacUserRightRelation> findByRoleId(Integer id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByRoleId"))
    .queryParam("id",id)
;  List<RbacUserRightRelation> aux = restTemplate.getForObject(builder.toUriString(), List<RbacUserRightRelation>.class);

 return aux;
}


}