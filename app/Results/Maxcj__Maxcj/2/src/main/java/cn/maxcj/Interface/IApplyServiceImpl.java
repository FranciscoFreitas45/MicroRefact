package cn.maxcj.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.maxcj.Interface.IApplyService;
public class IApplyServiceImpl implements IApplyService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public boolean apply_exist(Integer userid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/apply_exist"))
    .queryParam("userid",userid)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public Object selectById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/selectById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object insert(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/insert"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}