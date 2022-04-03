package com.uec.imonitor.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.uec.imonitor.Interface.IDataSearch;
public class IDataSearchImpl implements IDataSearch{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public BaseQueryResult<T> phraseSearcher(QueryParams<T> queryParams){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/phraseSearcher"))
    .queryParam("queryParams",queryParams)
;  BaseQueryResult<T> aux = restTemplate.getForObject(builder.toUriString(), BaseQueryResult<T>.class);

 return aux;
}


public BaseQueryResult<T> textHistogramSearcher(QueryParams<T> queryParams){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/textHistogramSearcher"))
    .queryParam("queryParams",queryParams)
;  BaseQueryResult<T> aux = restTemplate.getForObject(builder.toUriString(), BaseQueryResult<T>.class);

 return aux;
}


}