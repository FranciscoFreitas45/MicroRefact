import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class ClaimStructureDetailRepoImpl implements ClaimStructureDetailRepo{

 private RestTemplate restTemplate;

  String url = "http://24";


public List<ClaimStructureDetail> getClaimStructureDetailByEmpId(int empId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getClaimStructureDetailByEmpId"))
    .queryParam("empId",empId);
  List<ClaimStructureDetail> aux = restTemplate.getForObject(builder.toUriString(), List<ClaimStructureDetail>.class)

 return aux;
}


}