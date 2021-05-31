import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class GetAdvanceRepoImpl implements GetAdvanceRepo{

 private RestTemplate restTemplate;

  String url = "http://21";


public List<GetAdvance> getSpecEmpAdvForReport(int companyId,int month,int year,int locId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSpecEmpAdvForReport"))
    .queryParam("companyId",companyId)
    .queryParam("month",month)
    .queryParam("year",year)
    .queryParam("locId",locId);
  List<GetAdvance> aux = restTemplate.getForObject(builder.toUriString(), List<GetAdvance>.class)

 return aux;
}


}