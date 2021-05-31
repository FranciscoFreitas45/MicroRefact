import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class LeaveApplyRepositoryImpl implements LeaveApplyRepository{

 private RestTemplate restTemplate;

  String url = "http://1";


public List<LeaveApply> getleavetListForAttndace(String fromDate,String toDate,int empId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getleavetListForAttndace"))
    .queryParam("fromDate",fromDate)
    .queryParam("toDate",toDate)
    .queryParam("empId",empId);
  List<LeaveApply> aux = restTemplate.getForObject(builder.toUriString(), List<LeaveApply>.class)

 return aux;
}


}