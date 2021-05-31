import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class LeaveApplyRepositoryImpl implements LeaveApplyRepository{

 private RestTemplate restTemplate;

  String url = "http://1";


public List<LeaveApply> findByCalYrIdAndDelStatusAndEmpId(int calYr,int i,int empId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByCalYrIdAndDelStatusAndEmpId"))
    .queryParam("calYr",calYr)
    .queryParam("i",i)
    .queryParam("empId",empId);
  List<LeaveApply> aux = restTemplate.getForObject(builder.toUriString(), List<LeaveApply>.class)

 return aux;
}


}