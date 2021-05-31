import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class QiniuServiceImpl implements QiniuService{

 private RestTemplate restTemplate;

  String url = "http://4";


public String token(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/token"))
  String aux = restTemplate.getForObject(builder.toUriString(), String.class)

 return aux;
}


}