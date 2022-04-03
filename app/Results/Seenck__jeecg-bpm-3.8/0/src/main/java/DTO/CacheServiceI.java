package DTO;
 public interface CacheServiceI {

 public  String SYSTEM_BASE_CACHE;

 public  String TAG_CACHE;

 public  String FOREVER_CACHE;

 public  String SYS_AUTH_CACHE;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://10";


public Object get(String cacheName,Object key)
;

public void put(String cacheName,Object key,Object value)

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/put"))

.queryParam("cacheName",cacheName)
.queryParam("key",key)
.queryParam("value",value)
;
restTemplate.put(builder.toUriString(),null);
}
;

}