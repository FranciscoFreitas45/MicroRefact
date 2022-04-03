package cn.gson.oasys.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.Interface.AttendceDao;
public class AttendceDaoImpl implements AttendceDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public Attends findlastest(String date,long userid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findlastest"))
    .queryParam("date",date)
    .queryParam("userid",userid)
;  Attends aux = restTemplate.getForObject(builder.toUriString(), Attends.class);

 return aux;
}


}