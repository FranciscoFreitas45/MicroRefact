package org.danyuan.application.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.danyuan.application.Interface.SysDbmsTabsMergeInfoService;
public class SysDbmsTabsMergeInfoServiceImpl implements SysDbmsTabsMergeInfoService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public Object findOne(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public List<SysDbmsTabsColsInfo> page1(Pagination<SysDbmsTabsMergeInfo> vo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/page1"))
    .queryParam("vo",vo)
;  List<SysDbmsTabsColsInfo> aux = restTemplate.getForObject(builder.toUriString(), List<SysDbmsTabsColsInfo>.class);

 return aux;
}


public List<SysDbmsTabsColsInfo> page2(Pagination<SysDbmsTabsMergeInfo> vo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/page2"))
    .queryParam("vo",vo)
;  List<SysDbmsTabsColsInfo> aux = restTemplate.getForObject(builder.toUriString(), List<SysDbmsTabsColsInfo>.class);

 return aux;
}


public String merge(Pagination<SysDbmsTabsMergeInfo> vo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/merge"))
    .queryParam("vo",vo)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public String loadSql(SysDbmsTabsMergeInfo vo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/loadSql"))
    .queryParam("vo",vo)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}