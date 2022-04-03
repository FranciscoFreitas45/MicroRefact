package cn.maxcj.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.maxcj.Interface.IDeptService;
public class IDeptServiceImpl implements IDeptService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public List<Map<String,Object>> clublist(Integer categoryId,String condition){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/clublist"))
    .queryParam("categoryId",categoryId)
    .queryParam("condition",condition)
;  List<Map<String,Object>> aux = restTemplate.getForObject(builder.toUriString(), List<Map<String,Object>>.class);

 return aux;
}


}