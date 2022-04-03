package cn.maxcj.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.maxcj.Interface.ActivityMapper;
public class ActivityMapperImpl implements ActivityMapper{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public String getActivityName(Integer activity_id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getActivityName"))
    .queryParam("activity_id",activity_id)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}