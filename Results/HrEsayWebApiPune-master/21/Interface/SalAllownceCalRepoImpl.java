import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class SalAllownceCalRepoImpl implements SalAllownceCalRepo{

 private RestTemplate restTemplate;

  String url = "http://19";


public List<SalAllownceCal> findByEmpId(int i){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByEmpId"))
    .queryParam("i",i);
  List<SalAllownceCal> aux = restTemplate.getForObject(builder.toUriString(), List<SalAllownceCal>.class)

 return aux;
}


}