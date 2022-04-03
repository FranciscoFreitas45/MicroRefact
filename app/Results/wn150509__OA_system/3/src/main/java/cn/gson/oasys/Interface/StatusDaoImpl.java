package cn.gson.oasys.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.Interface.StatusDao;
public class StatusDaoImpl implements StatusDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public String findname(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findname"))
    .queryParam("id",id)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public List<SystemStatusList> findByStatusModel(String statusModel){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByStatusModel"))
    .queryParam("statusModel",statusModel)
;  List<SystemStatusList> aux = restTemplate.getForObject(builder.toUriString(), List<SystemStatusList>.class);

 return aux;
}


public Object findOne(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}