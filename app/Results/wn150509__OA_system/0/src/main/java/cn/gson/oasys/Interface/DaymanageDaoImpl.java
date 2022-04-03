package cn.gson.oasys.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.Interface.DaymanageDao;
public class DaymanageDaoImpl implements DaymanageDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://12";


public Page<ScheduleList> findByUser(User user,Pageable pa){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUser"))
    .queryParam("user",user)
    .queryParam("pa",pa)
;  Page<ScheduleList> aux = restTemplate.getForObject(builder.toUriString(), Page<ScheduleList>.class);

 return aux;
}


public Page<ScheduleList> findByUsers(List<User> users,Pageable pa){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUsers"))
    .queryParam("users",users)
    .queryParam("pa",pa)
;  Page<ScheduleList> aux = restTemplate.getForObject(builder.toUriString(), Page<ScheduleList>.class);

 return aux;
}


}