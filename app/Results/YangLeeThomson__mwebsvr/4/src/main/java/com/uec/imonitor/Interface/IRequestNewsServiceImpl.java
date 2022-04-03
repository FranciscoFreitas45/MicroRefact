package com.uec.imonitor.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.uec.imonitor.Interface.IRequestNewsService;
public class IRequestNewsServiceImpl implements IRequestNewsService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public List<RequestNewsDetail> listDetailByIds(List<Integer> idList){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/listDetailByIds"))
    .queryParam("idList",idList)
;  List<RequestNewsDetail> aux = restTemplate.getForObject(builder.toUriString(), List<RequestNewsDetail>.class);

 return aux;
}


}