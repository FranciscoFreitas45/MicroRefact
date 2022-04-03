package cn.gson.oasys.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.Interface.TaskDao;
public class TaskDaoImpl implements TaskDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Integer countfinish(Long status,Long userid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/countfinish"))
    .queryParam("status",status)
    .queryParam("userid",userid)
;  Integer aux = restTemplate.getForObject(builder.toUriString(), Integer.class);

 return aux;
}


}