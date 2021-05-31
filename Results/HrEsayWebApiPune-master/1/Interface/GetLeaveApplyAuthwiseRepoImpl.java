import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class GetLeaveApplyAuthwiseRepoImpl implements GetLeaveApplyAuthwiseRepo{

 private RestTemplate restTemplate;

  String url = "http://8";


public List<GetLeaveApplyAuthwise> getLeaveApplyList(int empId,int currYrId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getLeaveApplyList"))
    .queryParam("empId",empId)
    .queryParam("currYrId",currYrId);
  List<GetLeaveApplyAuthwise> aux = restTemplate.getForObject(builder.toUriString(), List<GetLeaveApplyAuthwise>.class)

 return aux;
}


public List<GetLeaveApplyAuthwise> getLeaveApplyList2(int empId,int currYrId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getLeaveApplyList2"))
    .queryParam("empId",empId)
    .queryParam("currYrId",currYrId);
  List<GetLeaveApplyAuthwise> aux = restTemplate.getForObject(builder.toUriString(), List<GetLeaveApplyAuthwise>.class)

 return aux;
}


}