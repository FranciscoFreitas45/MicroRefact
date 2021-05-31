import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class SalaryTypesMasterRepoImpl implements SalaryTypesMasterRepo{

 private RestTemplate restTemplate;

  String url = "http://7";


public List<SalaryTypesMaster> findAllByDelStatus(int i){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllByDelStatus"))
    .queryParam("i",i);
  List<SalaryTypesMaster> aux = restTemplate.getForObject(builder.toUriString(), List<SalaryTypesMaster>.class)

 return aux;
}


}