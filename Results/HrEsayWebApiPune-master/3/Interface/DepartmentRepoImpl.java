import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class DepartmentRepoImpl implements DepartmentRepo{

 private RestTemplate restTemplate;

  String url = "http://9";


public List<Department> findByNameAndCompanyIdAndDepartIdNot(String trim,int compId,int primaryKey){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByNameAndCompanyIdAndDepartIdNot"))
    .queryParam("trim",trim)
    .queryParam("compId",compId)
    .queryParam("primaryKey",primaryKey);
  List<Department> aux = restTemplate.getForObject(builder.toUriString(), List<Department>.class)

 return aux;
}


public List<Department> findByNameAndCompanyId(String dept,int compId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByNameAndCompanyId"))
    .queryParam("dept",dept)
    .queryParam("compId",compId);
  List<Department> aux = restTemplate.getForObject(builder.toUriString(), List<Department>.class)

 return aux;
}


}