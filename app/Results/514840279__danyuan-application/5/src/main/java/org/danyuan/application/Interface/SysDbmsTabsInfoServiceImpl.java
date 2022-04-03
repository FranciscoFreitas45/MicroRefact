package org.danyuan.application.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.danyuan.application.Interface.SysDbmsTabsInfoService;
public class SysDbmsTabsInfoServiceImpl implements SysDbmsTabsInfoService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public SysDbmsTabsInfo findOne(SysDbmsTabsInfo info){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))
    .queryParam("info",info)
;  SysDbmsTabsInfo aux = restTemplate.getForObject(builder.toUriString(), SysDbmsTabsInfo.class);

 return aux;
}


}