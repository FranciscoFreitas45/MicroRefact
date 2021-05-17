import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class SettingRepoImpl implements SettingRepo{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public Setting findByKey(String key){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByKey"))
    .queryParam("key",key);
  Setting aux = restTemplate.getForObject(builder.toUriString(), Setting.class)

 return aux;
}


}