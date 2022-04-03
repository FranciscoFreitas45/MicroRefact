package com.qidian.hcm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.qidian.hcm.Interface.CommonRepositoryUtil;
public class CommonRepositoryUtilImpl implements CommonRepositoryUtil{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public Page<T> pageByNative(Class clazz,String querySql,String countSql,Map<String,Object> params,int pageNo,int pageSize){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/pageByNative"))
    .queryParam("clazz",clazz)
    .queryParam("querySql",querySql)
    .queryParam("countSql",countSql)
    .queryParam("params",params)
    .queryParam("pageNo",pageNo)
    .queryParam("pageSize",pageSize)
;  Page<T> aux = restTemplate.getForObject(builder.toUriString(), Page<T>.class);

 return aux;
}


}