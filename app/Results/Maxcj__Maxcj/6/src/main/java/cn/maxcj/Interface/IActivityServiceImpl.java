package cn.maxcj.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.maxcj.Interface.IActivityService;
public class IActivityServiceImpl implements IActivityService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public List<Map<String,Object>> activity_club(Integer deptid,String condition){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/activity_club"))
    .queryParam("deptid",deptid)
    .queryParam("condition",condition)
;  List<Map<String,Object>> aux = restTemplate.getForObject(builder.toUriString(), List<Map<String,Object>>.class);

 return aux;
}


}