package com.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.Interface.ICommonDao;
public class ICommonDaoImpl implements ICommonDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public int update(String sql,Map<String,?> paramMap){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/update"))
    .queryParam("sql",sql)
    .queryParam("paramMap",paramMap)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}