import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class QiniuPropertiesImpl implements QiniuProperties{

 private RestTemplate restTemplate;

  String url = "http://4";


public String getUrl(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUrl"))
  String aux = restTemplate.getForObject(builder.toUriString(), String.class)

 return aux;
}


}