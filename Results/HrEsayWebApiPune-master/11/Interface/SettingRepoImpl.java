import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class SettingRepoImpl implements SettingRepo{

 private RestTemplate restTemplate;

  String url = "http://4";


public List<Setting> findByGroup(String string){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByGroup"))
    .queryParam("string",string);
  List<Setting> aux = restTemplate.getForObject(builder.toUriString(), List<Setting>.class)

 return aux;
}


public Setting findByKey(String key){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByKey"))
    .queryParam("key",key);
  Setting aux = restTemplate.getForObject(builder.toUriString(), Setting.class)

 return aux;
}


}