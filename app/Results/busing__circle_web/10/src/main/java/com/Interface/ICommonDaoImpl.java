package com.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.Interface.ICommonDao;
public class ICommonDaoImpl implements ICommonDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public int[] batchUpdate(String sql,List<Map<String,?>> paramListMap){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/batchUpdate"))
    .queryParam("sql",sql)
    .queryParam("paramListMap",paramListMap)
;  int[] aux = restTemplate.getForObject(builder.toUriString(), int[].class);

 return aux;
}


}