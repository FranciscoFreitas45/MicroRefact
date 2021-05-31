import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class GetAdvanceListRepoImpl implements GetAdvanceListRepo{

 private RestTemplate restTemplate;

  String url = "http://22";


public List<GetAdvanceList> getAdvanceList(int month,int year,List<Integer> empIds){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getAdvanceList"))
    .queryParam("month",month)
    .queryParam("year",year)
    .queryParam("empIds",empIds);
  List<GetAdvanceList> aux = restTemplate.getForObject(builder.toUriString(), List<GetAdvanceList>.class)

 return aux;
}


}