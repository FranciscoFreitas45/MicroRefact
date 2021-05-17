import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class GetEmployeeDetailsRepoImpl implements GetEmployeeDetailsRepo{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public List<GetEmployeeDetails> getEmpListByLocId(int locId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("getEmpListByLocId"))
    .queryParam("locId",locId);
  List<GetEmployeeDetails> aux = restTemplate.getForObject(builder.toUriString(), List<GetEmployeeDetails>.class)

 return aux;
}


}