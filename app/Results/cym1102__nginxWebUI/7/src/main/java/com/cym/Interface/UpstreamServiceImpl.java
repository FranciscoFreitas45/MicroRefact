package com.cym.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cym.Interface.UpstreamService;
public class UpstreamServiceImpl implements UpstreamService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public List<Upstream> getListByProxyType(Integer proxyType){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getListByProxyType"))
    .queryParam("proxyType",proxyType)
;  List<Upstream> aux = restTemplate.getForObject(builder.toUriString(), List<Upstream>.class);

 return aux;
}


public List<UpstreamServer> getUpstreamServers(String id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUpstreamServers"))
    .queryParam("id",id)
;  List<UpstreamServer> aux = restTemplate.getForObject(builder.toUriString(), List<UpstreamServer>.class);

 return aux;
}


}