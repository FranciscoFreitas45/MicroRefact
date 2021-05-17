import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class IDeptServiceImpl implements IDeptService{

 private RestTemplate restTemplate;

  String url = "http://3";


public Department findByDeptName(String deptName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByDeptName"))
    .queryParam("deptName",deptName);
  Department aux = restTemplate.getForObject(builder.toUriString(), Department.class)

 return aux;
}


}