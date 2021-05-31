import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class GetClaimApplyAuthwiseRepoImpl implements GetClaimApplyAuthwiseRepo{

 private RestTemplate restTemplate;

  String url = "http://14";


public List<GetClaimApplyAuthwise> getClaimApplyList(int empId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getClaimApplyList"))
    .queryParam("empId",empId);
  List<GetClaimApplyAuthwise> aux = restTemplate.getForObject(builder.toUriString(), List<GetClaimApplyAuthwise>.class)

 return aux;
}


public List<GetClaimApplyAuthwise> getClaimApplyListForPendingForManager(int empId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getClaimApplyListForPendingForManager"))
    .queryParam("empId",empId);
  List<GetClaimApplyAuthwise> aux = restTemplate.getForObject(builder.toUriString(), List<GetClaimApplyAuthwise>.class)

 return aux;
}


public List<GetClaimApplyAuthwise> getClaimApplyListForPendingForAdmin(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getClaimApplyListForPendingForAdmin"))
  List<GetClaimApplyAuthwise> aux = restTemplate.getForObject(builder.toUriString(), List<GetClaimApplyAuthwise>.class)

 return aux;
}


public List<GetClaimApplyAuthwise> getClaimApplyList2(int empId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getClaimApplyList2"))
    .queryParam("empId",empId);
  List<GetClaimApplyAuthwise> aux = restTemplate.getForObject(builder.toUriString(), List<GetClaimApplyAuthwise>.class)

 return aux;
}


public GetClaimApplyAuthwise getClaimApplyDetails(int claimId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getClaimApplyDetails"))
    .queryParam("claimId",claimId);
  GetClaimApplyAuthwise aux = restTemplate.getForObject(builder.toUriString(), GetClaimApplyAuthwise.class)

 return aux;
}


}