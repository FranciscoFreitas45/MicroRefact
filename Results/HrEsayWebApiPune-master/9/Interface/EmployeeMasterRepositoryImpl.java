import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class EmployeeMasterRepositoryImpl implements EmployeeMasterRepository{

 private RestTemplate restTemplate;

  String url = "http://3";


public int getEmpInfoByDesigId(int desigId,int companyId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEmpInfoByDesigId"))
    .queryParam("desigId",desigId)
    .queryParam("companyId",companyId);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


public int getEmpInfoByContractId(int contractId,int companyId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEmpInfoByContractId"))
    .queryParam("contractId",contractId)
    .queryParam("companyId",companyId);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


public int getEmpInfoByDepartment(int deptId,int companyId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEmpInfoByDepartment"))
    .queryParam("deptId",deptId)
    .queryParam("companyId",companyId);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


}