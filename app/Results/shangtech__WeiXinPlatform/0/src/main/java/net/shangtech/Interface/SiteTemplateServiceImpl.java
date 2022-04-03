package net.shangtech.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import net.shangtech.Interface.SiteTemplateService;
public class SiteTemplateServiceImpl implements SiteTemplateService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public List<SiteTemplate> findByType(int type){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByType"))
    .queryParam("type",type)
;  List<SiteTemplate> aux = restTemplate.getForObject(builder.toUriString(), List<SiteTemplate>.class);

 return aux;
}


public Object find(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/find"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}