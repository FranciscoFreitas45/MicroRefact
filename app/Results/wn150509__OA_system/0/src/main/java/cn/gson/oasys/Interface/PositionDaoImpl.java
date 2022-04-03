package cn.gson.oasys.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.Interface.PositionDao;
public class PositionDaoImpl implements PositionDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public Object findAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object findOne(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public List<Position> findByDeptidAndNameNotLike(Long deptid,String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByDeptidAndNameNotLike"))
    .queryParam("deptid",deptid)
    .queryParam("name",name)
;  List<Position> aux = restTemplate.getForObject(builder.toUriString(), List<Position>.class);

 return aux;
}


}