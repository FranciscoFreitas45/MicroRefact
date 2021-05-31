import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class PhCreditDetailServiceImpl implements PhCreditDetailService{

 private RestTemplate restTemplate;

  String url = "http://0";


public PhCreditDetail save(PhCreditDetail phCreditDetail){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("phCreditDetail",phCreditDetail);
  PhCreditDetail aux = restTemplate.getForObject(builder.toUriString(), PhCreditDetail.class)

 return aux;
}


}