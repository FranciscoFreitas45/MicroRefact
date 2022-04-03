package com.wxcrm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.wxcrm.Interface.IJdbcDao;
public class IJdbcDaoImpl implements IJdbcDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public void queryForPage(Page page){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryForPage"))
    .queryParam("page",page)
;
  restTemplate.put(builder.toUriString(), null);
}


public int delete(String sql,Object[] args){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delete"))
    .queryParam("sql",sql)
    .queryParam("args",args)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public List<Map<String,Object>> queryForList(String sql,Object[] args){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryForList"))
    .queryParam("sql",sql)
    .queryParam("args",args)
;  List<Map<String,Object>> aux = restTemplate.getForObject(builder.toUriString(), List<Map<String,Object>>.class);

 return aux;
}


}