package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.CacheInstance;
public class CacheInstanceImpl implements CacheInstance{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public CacheBean getAgentStatusCacheBean(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAgentStatusCacheBean"))
;  CacheBean aux = restTemplate.getForObject(builder.toUriString(), CacheBean.class);

 return aux;
}


public CacheBean getAgentUserCacheBean(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAgentUserCacheBean"))
;  CacheBean aux = restTemplate.getForObject(builder.toUriString(), CacheBean.class);

 return aux;
}


public CacheBean getOnlineCacheBean(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getOnlineCacheBean"))
;  CacheBean aux = restTemplate.getForObject(builder.toUriString(), CacheBean.class);

 return aux;
}


public CacheBean getSystemCacheBean(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSystemCacheBean"))
;  CacheBean aux = restTemplate.getForObject(builder.toUriString(), CacheBean.class);

 return aux;
}


public CacheBean getIMRCacheBean(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getIMRCacheBean"))
;  CacheBean aux = restTemplate.getForObject(builder.toUriString(), CacheBean.class);

 return aux;
}


public CacheBean getCallCenterCacheBean(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getCallCenterCacheBean"))
;  CacheBean aux = restTemplate.getForObject(builder.toUriString(), CacheBean.class);

 return aux;
}


public CacheBean getCallCenterAgentCacheBean(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getCallCenterAgentCacheBean"))
;  CacheBean aux = restTemplate.getForObject(builder.toUriString(), CacheBean.class);

 return aux;
}


public CacheBean getApiUserCacheBean(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getApiUserCacheBean"))
;  CacheBean aux = restTemplate.getForObject(builder.toUriString(), CacheBean.class);

 return aux;
}


public CacheBean getJobCacheBean(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getJobCacheBean"))
;  CacheBean aux = restTemplate.getForObject(builder.toUriString(), CacheBean.class);

 return aux;
}


public CacheBean getCallOutCacheBean(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getCallOutCacheBean"))
;  CacheBean aux = restTemplate.getForObject(builder.toUriString(), CacheBean.class);

 return aux;
}


public CacheBean getQcQueueBean(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getQcQueueBean"))
;  CacheBean aux = restTemplate.getForObject(builder.toUriString(), CacheBean.class);

 return aux;
}


}