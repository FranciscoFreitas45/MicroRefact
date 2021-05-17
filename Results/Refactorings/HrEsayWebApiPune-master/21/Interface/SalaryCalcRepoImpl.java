import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class SalaryCalcRepoImpl implements SalaryCalcRepo{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public List<SalaryCalc> findAllByEmpId(int empId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findAllByEmpId"))
    .queryParam("empId",empId);
  List<SalaryCalc> aux = restTemplate.getForObject(builder.toUriString(), List<SalaryCalc>.class)

 return aux;
}


}