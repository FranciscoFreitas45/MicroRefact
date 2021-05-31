import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class GetEmployeeDetailsRepoImpl implements GetEmployeeDetailsRepo{

 private RestTemplate restTemplate;

  String url = "http://7";


public List<GetEmployeeDetails> getEmpDetailListByBonusIdAssignedBonus(int bonusId,List<Integer> locId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEmpDetailListByBonusIdAssignedBonus"))
    .queryParam("bonusId",bonusId)
    .queryParam("locId",locId);
  List<GetEmployeeDetails> aux = restTemplate.getForObject(builder.toUriString(), List<GetEmployeeDetails>.class)

 return aux;
}


public List<GetEmployeeDetails> getEmpDetailListByBonusId(int bonusId,List<Integer> locId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEmpDetailListByBonusId"))
    .queryParam("bonusId",bonusId)
    .queryParam("locId",locId);
  List<GetEmployeeDetails> aux = restTemplate.getForObject(builder.toUriString(), List<GetEmployeeDetails>.class)

 return aux;
}


public List<GetEmployeeDetails> getEmpDetailList(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getEmpDetailList"))
  List<GetEmployeeDetails> aux = restTemplate.getForObject(builder.toUriString(), List<GetEmployeeDetails>.class)

 return aux;
}


}