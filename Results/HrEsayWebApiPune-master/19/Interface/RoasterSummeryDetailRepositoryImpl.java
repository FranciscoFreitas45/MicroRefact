import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class RoasterSummeryDetailRepositoryImpl implements RoasterSummeryDetailRepository{

 private RestTemplate restTemplate;

  String url = "http://17";


public List<RoasterSummeryDetail> getRoasterSummeryDetailForPayRoll(String fromDate,String toDate,int design){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getRoasterSummeryDetailForPayRoll"))
    .queryParam("fromDate",fromDate)
    .queryParam("toDate",toDate)
    .queryParam("design",design);
  List<RoasterSummeryDetail> aux = restTemplate.getForObject(builder.toUriString(), List<RoasterSummeryDetail>.class)

 return aux;
}


}