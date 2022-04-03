package cn.gson.oasys.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.Interface.StatusDao;
public class StatusDaoImpl implements StatusDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public List<SystemStatusList> findByStatusModel(String statusModel){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByStatusModel"))
    .queryParam("statusModel",statusModel)
;  List<SystemStatusList> aux = restTemplate.getForObject(builder.toUriString(), List<SystemStatusList>.class);

 return aux;
}


}