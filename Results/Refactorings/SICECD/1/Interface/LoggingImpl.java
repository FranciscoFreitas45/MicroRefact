import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class LoggingImpl implements Logging{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public void setTrace(String action,String comments){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("setTrace"))
    .queryParam("action",action)
    .queryParam("comments",comments);

  restTemplate.put(builder.toUriString(), null)



}