import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class IInStorageServiceImpl implements IInStorageService{

 private RestTemplate restTemplate;

  String url = "http://4";


public void save(InStorage inStorage){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("inStorage",inStorage);

  restTemplate.put(builder.toUriString(), null)



}