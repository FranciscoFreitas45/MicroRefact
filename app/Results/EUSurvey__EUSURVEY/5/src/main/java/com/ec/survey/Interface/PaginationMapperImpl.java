package com.ec.survey.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.ec.survey.Interface.PaginationMapper;
public class PaginationMapperImpl implements PaginationMapper{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public SqlPagination toSqlPagination(Paging paging){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toSqlPagination"))
    .queryParam("paging",paging)
;  SqlPagination aux = restTemplate.getForObject(builder.toUriString(), SqlPagination.class);

 return aux;
}


}