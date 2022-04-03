package cn.gson.oasys.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.Interface.ScheduleDao;
public class ScheduleDaoImpl implements ScheduleDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public List<ScheduleList> findstart(long userid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findstart"))
    .queryParam("userid",userid)
;  List<ScheduleList> aux = restTemplate.getForObject(builder.toUriString(), List<ScheduleList>.class);

 return aux;
}


}