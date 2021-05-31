import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class GetEmpInfoRepoImpl implements GetEmpInfoRepo{

 private RestTemplate restTemplate;

  String url = "http://3";


public List<GetEmployeeInfo> getEmpListByCompanyId(int companyId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEmpListByCompanyId"))
    .queryParam("companyId",companyId);
  List<GetEmployeeInfo> aux = restTemplate.getForObject(builder.toUriString(), List<GetEmployeeInfo>.class)

 return aux;
}


public List<GetEmployeeInfo> getEmpIdListByCompanyId(int companyId,List<GetEmployeeAuthorityWise> empIdList){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEmpIdListByCompanyId"))
    .queryParam("companyId",companyId)
    .queryParam("empIdList",empIdList);
  List<GetEmployeeInfo> aux = restTemplate.getForObject(builder.toUriString(), List<GetEmployeeInfo>.class)

 return aux;
}


public List<GetEmployeeInfo> getEmpListByCompanyIdForAuth(int companyId,List<Integer> locIdList){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEmpListByCompanyIdForAuth"))
    .queryParam("companyId",companyId)
    .queryParam("locIdList",locIdList);
  List<GetEmployeeInfo> aux = restTemplate.getForObject(builder.toUriString(), List<GetEmployeeInfo>.class)

 return aux;
}


public List<GetEmployeeInfo> getEmpListByCompanyIdForAuthClaim(int companyId,List<Integer> locIdList){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEmpListByCompanyIdForAuthClaim"))
    .queryParam("companyId",companyId)
    .queryParam("locIdList",locIdList);
  List<GetEmployeeInfo> aux = restTemplate.getForObject(builder.toUriString(), List<GetEmployeeInfo>.class)

 return aux;
}


public List<GetEmployeeInfo> getEmpIdListByCompanyIdForClaim(int companyId,List<GetEmployeeAuthorityWise> empIdList){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEmpIdListByCompanyIdForClaim"))
    .queryParam("companyId",companyId)
    .queryParam("empIdList",empIdList);
  List<GetEmployeeInfo> aux = restTemplate.getForObject(builder.toUriString(), List<GetEmployeeInfo>.class)

 return aux;
}


public GetEmployeeInfo getEmpByEmpId(int empId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEmpByEmpId"))
    .queryParam("empId",empId);
  GetEmployeeInfo aux = restTemplate.getForObject(builder.toUriString(), GetEmployeeInfo.class)

 return aux;
}


}