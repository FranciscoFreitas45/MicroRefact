import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class LoanMainRepoImpl implements LoanMainRepo{

 private RestTemplate restTemplate;

  String url = "http://18";


public List<LoanMain> getLoanList(int empIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getLoanList"))
    .queryParam("empIds",empIds);
  List<LoanMain> aux = restTemplate.getForObject(builder.toUriString(), List<LoanMain>.class)

 return aux;
}


public Object saveAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveAll"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class)

 return aux;
}


}