package com.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.Interface.IGoodService;
public class IGoodServiceImpl implements IGoodService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://12";


public List<Map<String,Object>> queryCurrentBatchGoodList(Page page,int batchId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryCurrentBatchGoodList"))
    .queryParam("page",page)
    .queryParam("batchId",batchId)
;  List<Map<String,Object>> aux = restTemplate.getForObject(builder.toUriString(), List<Map<String,Object>>.class);

 return aux;
}


}