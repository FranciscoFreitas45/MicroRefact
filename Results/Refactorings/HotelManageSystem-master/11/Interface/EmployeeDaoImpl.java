import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class EmployeeDaoImpl implements EmployeeDao{

 private RestTemplate restTemplate;

  String url = "http://2";


public Employee findByUserName(String userName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByUserName"))
    .queryParam("userName",userName);
  Employee aux = restTemplate.getForObject(builder.toUriString(), Employee.class)

 return aux;
}


}