import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class EmployeeMasterRepositoryImpl implements EmployeeMasterRepository{

 private RestTemplate restTemplate;

  String url = "http://3";


public List<EmployeeMaster> getemplistwhichisnotyearend(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getemplistwhichisnotyearend"))
  List<EmployeeMaster> aux = restTemplate.getForObject(builder.toUriString(), List<EmployeeMaster>.class)

 return aux;
}


public List<EmployeeMaster> getemplistwhichisnotyearendByEmpId(int locId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getemplistwhichisnotyearendByEmpId"))
    .queryParam("locId",locId);
  List<EmployeeMaster> aux = restTemplate.getForObject(builder.toUriString(), List<EmployeeMaster>.class)

 return aux;
}


}