import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class WeeklyOffRepoImpl implements WeeklyOffRepo{

 private RestTemplate restTemplate;

  String url = "http://10";


public List<WeeklyOff> getWeeklyOffList(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getWeeklyOffList"))
  List<WeeklyOff> aux = restTemplate.getForObject(builder.toUriString(), List<WeeklyOff>.class)

 return aux;
}


}