import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class AssetLogRepoImpl implements AssetLogRepo{

 private RestTemplate restTemplate;

  String url = "http://23";


public AssetLog save(AssetLog log){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("log",log);
  AssetLog aux = restTemplate.getForObject(builder.toUriString(), AssetLog.class)

 return aux;
}


}