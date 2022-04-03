package cn.gson.oasys.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.Interface.DaymanageServices;
public class DaymanageServicesImpl implements DaymanageServices{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public List<ScheduleList> aboutmeschedule(Long userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/aboutmeschedule"))
    .queryParam("userId",userId)
;  List<ScheduleList> aux = restTemplate.getForObject(builder.toUriString(), List<ScheduleList>.class);

 return aux;
}


}