import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class EmpSalAllowanceRepoImpl implements EmpSalAllowanceRepo{

 private RestTemplate restTemplate;

  String url = "http://7";


public List<EmpSalAllowance> findByDelStatus(int i){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByDelStatus"))
    .queryParam("i",i);
  List<EmpSalAllowance> aux = restTemplate.getForObject(builder.toUriString(), List<EmpSalAllowance>.class)

 return aux;
}


public List<EmpSalAllowance> findByDelStatusAndEmpId(int delStatus,List<Integer> empIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByDelStatusAndEmpId"))
    .queryParam("delStatus",delStatus)
    .queryParam("empIds",empIds);
  List<EmpSalAllowance> aux = restTemplate.getForObject(builder.toUriString(), List<EmpSalAllowance>.class)

 return aux;
}


}