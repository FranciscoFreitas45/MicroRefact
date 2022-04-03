package org.danyuan.application.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.danyuan.application.Interface.SysDbmsTabsColsInfoService;
public class SysDbmsTabsColsInfoServiceImpl implements SysDbmsTabsColsInfoService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public List<SysDbmsTabsColsInfo> findAll(SysDbmsTabsColsInfo info){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("info",info)
;  List<SysDbmsTabsColsInfo> aux = restTemplate.getForObject(builder.toUriString(), List<SysDbmsTabsColsInfo>.class);

 return aux;
}


}