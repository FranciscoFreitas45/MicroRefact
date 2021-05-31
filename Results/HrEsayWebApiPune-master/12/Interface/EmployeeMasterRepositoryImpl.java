import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class EmployeeMasterRepositoryImpl implements EmployeeMasterRepository{

 private RestTemplate restTemplate;

  String url = "http://3";


public List<EmployeeMaster> findByLocationIdAndDelStatus(int locId,int i){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByLocationIdAndDelStatus"))
    .queryParam("locId",locId)
    .queryParam("i",i);
  List<EmployeeMaster> aux = restTemplate.getForObject(builder.toUriString(), List<EmployeeMaster>.class)

 return aux;
}


}