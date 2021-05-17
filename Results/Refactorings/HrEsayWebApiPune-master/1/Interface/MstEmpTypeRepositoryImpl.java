import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class MstEmpTypeRepositoryImpl implements MstEmpTypeRepository{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public MstEmpType getTypeByempId(int empId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("getTypeByempId"))
    .queryParam("empId",empId);
  MstEmpType aux = restTemplate.getForObject(builder.toUriString(), MstEmpType.class)

 return aux;
}


}