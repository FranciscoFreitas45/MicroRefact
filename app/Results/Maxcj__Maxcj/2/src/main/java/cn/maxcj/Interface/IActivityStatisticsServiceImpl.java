package cn.maxcj.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.maxcj.Interface.IActivityStatisticsService;
public class IActivityStatisticsServiceImpl implements IActivityStatisticsService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://12";


public List<ActivityStatistics> list(Integer deptid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/list"))
    .queryParam("deptid",deptid)
;  List<ActivityStatistics> aux = restTemplate.getForObject(builder.toUriString(), List<ActivityStatistics>.class);

 return aux;
}


public Object deleteById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}