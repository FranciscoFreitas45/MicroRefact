package cn.gson.oasys.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.Interface.TaskuserDao;
public class TaskuserDaoImpl implements TaskuserDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public List<Taskuser> findByUserIdAndStatusId(User user,Integer id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUserIdAndStatusId"))
    .queryParam("user",user)
    .queryParam("id",id)
;  List<Taskuser> aux = restTemplate.getForObject(builder.toUriString(), List<Taskuser>.class);

 return aux;
}


}