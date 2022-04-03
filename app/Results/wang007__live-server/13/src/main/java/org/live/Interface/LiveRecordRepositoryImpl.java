package org.live.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.Interface.LiveRecordRepository;
public class LiveRecordRepositoryImpl implements LiveRecordRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public Object count(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/count"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Page<LiveRecordVo> find(PageRequest pageRequest,Map<String,Object> filter){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/find"))
    .queryParam("pageRequest",pageRequest)
    .queryParam("filter",filter)
;  Page<LiveRecordVo> aux = restTemplate.getForObject(builder.toUriString(), Page<LiveRecordVo>.class);

 return aux;
}


}