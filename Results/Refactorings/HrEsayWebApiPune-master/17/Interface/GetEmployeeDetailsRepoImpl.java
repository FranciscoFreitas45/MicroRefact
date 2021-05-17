import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class GetEmployeeDetailsRepoImpl implements GetEmployeeDetailsRepo{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public List<GetEmployeeDetails> getlistforinsertInitiallydriverInPlanRoute(int driverDesignatin,String date){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("getlistforinsertInitiallydriverInPlanRoute"))
    .queryParam("driverDesignatin",driverDesignatin)
    .queryParam("date",date);
  List<GetEmployeeDetails> aux = restTemplate.getForObject(builder.toUriString(), List<GetEmployeeDetails>.class)

 return aux;
}


}