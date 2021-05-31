import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class WeeklyOffRepoImpl implements WeeklyOffRepo{

 private RestTemplate restTemplate;

  String url = "http://10";


public List<WeeklyOff> findByExInt1AndDelStatus(int hoCatId,int i){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByExInt1AndDelStatus"))
    .queryParam("hoCatId",hoCatId)
    .queryParam("i",i);
  List<WeeklyOff> aux = restTemplate.getForObject(builder.toUriString(), List<WeeklyOff>.class)

 return aux;
}


public List<EmployeeMaster> findByExInt2AndDelStatus(int skillId,int i){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByExInt2AndDelStatus"))
    .queryParam("skillId",skillId)
    .queryParam("i",i);
  List<EmployeeMaster> aux = restTemplate.getForObject(builder.toUriString(), List<EmployeeMaster>.class)

 return aux;
}


}