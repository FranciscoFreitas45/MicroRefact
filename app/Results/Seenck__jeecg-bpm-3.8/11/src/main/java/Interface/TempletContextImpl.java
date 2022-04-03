package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.TempletContext;
public class TempletContextImpl implements TempletContext{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public void removeTemplateFromCache(String tableName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/removeTemplateFromCache"))
    .queryParam("tableName",tableName)
;
  restTemplate.put(builder.toUriString(), null);
}


}