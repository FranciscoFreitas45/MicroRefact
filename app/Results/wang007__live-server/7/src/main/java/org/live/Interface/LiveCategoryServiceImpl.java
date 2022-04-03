package org.live.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.Interface.LiveCategoryService;
public class LiveCategoryServiceImpl implements LiveCategoryService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public List<LiveCategoryVo> findLiveCategory4app(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findLiveCategory4app"))
;  List<LiveCategoryVo> aux = restTemplate.getForObject(builder.toUriString(), List<LiveCategoryVo>.class);

 return aux;
}


}