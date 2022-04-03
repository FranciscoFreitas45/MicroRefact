package cn.maxcj.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.maxcj.Interface.IActivityService;
public class IActivityServiceImpl implements IActivityService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public List<Activity> clublist(Integer deptid,String condition){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/clublist"))
    .queryParam("deptid",deptid)
    .queryParam("condition",condition)
;  List<Activity> aux = restTemplate.getForObject(builder.toUriString(), List<Activity>.class);

 return aux;
}


public Object deleteById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}