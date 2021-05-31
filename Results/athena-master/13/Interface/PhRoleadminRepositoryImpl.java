import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class PhRoleadminRepositoryImpl implements PhRoleadminRepository{

 private RestTemplate restTemplate;

  String url = "http://11";


public int deleteByRoleIds(List<Long> ids){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteByRoleIds"))
    .queryParam("ids",ids);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


}