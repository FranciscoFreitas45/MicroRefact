import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class EmpListForHolidayApproveRepoImpl implements EmpListForHolidayApproveRepo{

 private RestTemplate restTemplate;

  String url = "http://10";


public List<EmpListForHolidayApprove> getOptionalHolidayApprovalList(int sts){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getOptionalHolidayApprovalList"))
    .queryParam("sts",sts);
  List<EmpListForHolidayApprove> aux = restTemplate.getForObject(builder.toUriString(), List<EmpListForHolidayApprove>.class)

 return aux;
}


}