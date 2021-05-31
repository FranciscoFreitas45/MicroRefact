import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class EmpSalaryInfoRepoImpl implements EmpSalaryInfoRepo{

 private RestTemplate restTemplate;

  String url = "http://7";


public int updateLeaveDatainemployee(int empId,String leaveDate,String leaveReason,String lrEsic,String lrForPF){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateLeaveDatainemployee"))
    .queryParam("empId",empId)
    .queryParam("leaveDate",leaveDate)
    .queryParam("leaveReason",leaveReason)
    .queryParam("lrEsic",lrEsic)
    .queryParam("lrForPF",lrForPF);
  int aux = restTemplate.getForObject(builder.toUriString(), int.class)

 return aux;
}


}