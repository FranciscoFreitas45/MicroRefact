import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class GetEmployeeDetailsRepoImpl implements GetEmployeeDetailsRepo{

 private RestTemplate restTemplate;

  String url = "http://7";


public List<GetEmployeeDetails> getEmplistForAssignAuthority(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEmplistForAssignAuthority"))
  List<GetEmployeeDetails> aux = restTemplate.getForObject(builder.toUriString(), List<GetEmployeeDetails>.class)

 return aux;
}


public List<GetEmployeeDetails> getEmpListByCompanyIdForAuth(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEmpListByCompanyIdForAuth"))
  List<GetEmployeeDetails> aux = restTemplate.getForObject(builder.toUriString(), List<GetEmployeeDetails>.class)

 return aux;
}


public List<GetEmployeeDetails> getEmpInfoListForLeaveAuthLocId(List<Integer> locId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEmpInfoListForLeaveAuthLocId"))
    .queryParam("locId",locId);
  List<GetEmployeeDetails> aux = restTemplate.getForObject(builder.toUriString(), List<GetEmployeeDetails>.class)

 return aux;
}


public List<GetEmployeeDetails> getEmpListByCompanyIdAndEmpIdList(List<Integer> empIdList){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEmpListByCompanyIdAndEmpIdList"))
    .queryParam("empIdList",empIdList);
  List<GetEmployeeDetails> aux = restTemplate.getForObject(builder.toUriString(), List<GetEmployeeDetails>.class)

 return aux;
}


public List<GetEmployeeDetails> getAuthorityWiseEmpListByEmpId(int empId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAuthorityWiseEmpListByEmpId"))
    .queryParam("empId",empId);
  List<GetEmployeeDetails> aux = restTemplate.getForObject(builder.toUriString(), List<GetEmployeeDetails>.class)

 return aux;
}


public List<GetEmployeeDetails> getEmpListForClaimAuthByEmpId(int empId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEmpListForClaimAuthByEmpId"))
    .queryParam("empId",empId);
  List<GetEmployeeDetails> aux = restTemplate.getForObject(builder.toUriString(), List<GetEmployeeDetails>.class)

 return aux;
}


public List<GetEmployeeDetails> getAuthorityWiseEmpListByEmpIdForApp(int empId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAuthorityWiseEmpListByEmpIdForApp"))
    .queryParam("empId",empId);
  List<GetEmployeeDetails> aux = restTemplate.getForObject(builder.toUriString(), List<GetEmployeeDetails>.class)

 return aux;
}


}