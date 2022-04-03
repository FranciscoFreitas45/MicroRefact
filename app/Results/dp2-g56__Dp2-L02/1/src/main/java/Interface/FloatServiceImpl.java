package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.FloatService;
public class FloatServiceImpl implements FloatService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public domain.Float findOne(int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))
    .queryParam("id",id)
;  domain.Float aux = restTemplate.getForObject(builder.toUriString(), domain.Float.class);

 return aux;
}


public Boolean isUrl(String url){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isUrl"))
    .queryParam("url",url);
  Boolean aux = restTemplate.getForObject(builder.toUriString(), Boolean.class);

 return aux;
}


}