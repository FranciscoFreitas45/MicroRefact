import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class EmployeeMasterRepositoryImpl implements EmployeeMasterRepository{

 private RestTemplate restTemplate;

  String url = "http://3";


public List<EmployeeMaster> findByDelStatusAndCmpCodeAndSubCmpIdOrderByEmpIdDesc(int del,int companyId,int subCompId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByDelStatusAndCmpCodeAndSubCmpIdOrderByEmpIdDesc"))
    .queryParam("del",del)
    .queryParam("companyId",companyId)
    .queryParam("subCompId",subCompId);
  List<EmployeeMaster> aux = restTemplate.getForObject(builder.toUriString(), List<EmployeeMaster>.class)

 return aux;
}


}