import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class EmpSalaryInfoRepoImpl implements EmpSalaryInfoRepo{

 private RestTemplate restTemplate;

  String url = "http://7";


public EmpSalaryInfo findByEmpIdAndDelStatus(int empId,int del){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByEmpIdAndDelStatus"))
    .queryParam("empId",empId)
    .queryParam("del",del);
  EmpSalaryInfo aux = restTemplate.getForObject(builder.toUriString(), EmpSalaryInfo.class)

 return aux;
}


}