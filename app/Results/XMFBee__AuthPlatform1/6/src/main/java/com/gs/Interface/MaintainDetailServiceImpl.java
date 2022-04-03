package com.gs.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gs.Interface.MaintainDetailService;
public class MaintainDetailServiceImpl implements MaintainDetailService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public List<MaintainDetail> queryByFrontpage(Pager pager){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryByFrontpage"))
    .queryParam("pager",pager)
;  List<MaintainDetail> aux = restTemplate.getForObject(builder.toUriString(), List<MaintainDetail>.class);

 return aux;
}


}