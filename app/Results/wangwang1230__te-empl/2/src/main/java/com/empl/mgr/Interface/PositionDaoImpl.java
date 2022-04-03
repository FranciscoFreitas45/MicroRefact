package com.empl.mgr.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.empl.mgr.Interface.PositionDao;
public class PositionDaoImpl implements PositionDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public Object findUniqueByProperty(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findUniqueByProperty"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public List<PositionDto> findByDeptId(long deptId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByDeptId"))
    .queryParam("deptId",deptId)
;  List<PositionDto> aux = restTemplate.getForObject(builder.toUriString(), List<PositionDto>.class);

 return aux;
}


}