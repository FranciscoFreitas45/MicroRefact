package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.TempletContextWord;
public class TempletContextWordImpl implements TempletContextWord{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public String autoFormGenerateHtml(String tableName,String id,String mode){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/autoFormGenerateHtml"))
    .queryParam("tableName",tableName)
    .queryParam("id",id)
    .queryParam("mode",mode)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}