import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class LoanMainRepoImpl implements LoanMainRepo{

 private RestTemplate restTemplate;

  String url = "http://18";


public List<LoanMain> findByEmpIdAndDelStatus(int empId,int i){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByEmpIdAndDelStatus"))
    .queryParam("empId",empId)
    .queryParam("i",i);
  List<LoanMain> aux = restTemplate.getForObject(builder.toUriString(), List<LoanMain>.class)

 return aux;
}


}