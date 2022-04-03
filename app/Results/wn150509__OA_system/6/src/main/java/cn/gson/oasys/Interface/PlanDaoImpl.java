package cn.gson.oasys.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.Interface.PlanDao;
public class PlanDaoImpl implements PlanDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public List<Plan> findByUserlimit(long userid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUserlimit"))
    .queryParam("userid",userid)
;  List<Plan> aux = restTemplate.getForObject(builder.toUriString(), List<Plan>.class);

 return aux;
}


}