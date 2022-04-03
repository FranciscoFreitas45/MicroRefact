package com.empl.mgr.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.empl.mgr.Interface.DepartmentDao;
public class DepartmentDaoImpl implements DepartmentDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public Object findUniqueByProperty(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findUniqueByProperty"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public List<DepartmentSelectDto> findAllDepartment(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllDepartment"))
;  List<DepartmentSelectDto> aux = restTemplate.getForObject(builder.toUriString(), List<DepartmentSelectDto>.class);

 return aux;
}


public Object findById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}