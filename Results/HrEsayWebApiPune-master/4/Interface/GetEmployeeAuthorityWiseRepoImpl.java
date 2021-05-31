import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class GetEmployeeAuthorityWiseRepoImpl implements GetEmployeeAuthorityWiseRepo{

 private RestTemplate restTemplate;

  String url = "http://6";


public List<GetEmployeeAuthorityWise> getEmpIdList(int empId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEmpIdList"))
    .queryParam("empId",empId);
  List<GetEmployeeAuthorityWise> aux = restTemplate.getForObject(builder.toUriString(), List<GetEmployeeAuthorityWise>.class)

 return aux;
}


public List<GetEmployeeAuthorityWise> getEmpIdListInClaimAuth(int empId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEmpIdListInClaimAuth"))
    .queryParam("empId",empId);
  List<GetEmployeeAuthorityWise> aux = restTemplate.getForObject(builder.toUriString(), List<GetEmployeeAuthorityWise>.class)

 return aux;
}


}