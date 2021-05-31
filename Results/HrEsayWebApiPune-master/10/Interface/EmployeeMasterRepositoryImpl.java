import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class EmployeeMasterRepositoryImpl implements EmployeeMasterRepository{

 private RestTemplate restTemplate;

  String url = "http://3";


public EmployeeMaster findByEmpIdAndDelStatus(int empId,int del){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByEmpIdAndDelStatus"))
    .queryParam("empId",empId)
    .queryParam("del",del);
  EmployeeMaster aux = restTemplate.getForObject(builder.toUriString(), EmployeeMaster.class)

 return aux;
}


}