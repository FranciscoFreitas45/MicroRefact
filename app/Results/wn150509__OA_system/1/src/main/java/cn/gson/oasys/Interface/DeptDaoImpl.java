package cn.gson.oasys.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.Interface.DeptDao;
public class DeptDaoImpl implements DeptDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public Object findAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public String findname(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findname"))
    .queryParam("id",id)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}