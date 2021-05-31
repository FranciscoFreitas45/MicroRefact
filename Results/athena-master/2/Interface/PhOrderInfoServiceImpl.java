import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class PhOrderInfoServiceImpl implements PhOrderInfoService{

 private RestTemplate restTemplate;

  String url = "http://0";


public PhOrderInfo findByOrderNo(String orderNo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByOrderNo"))
    .queryParam("orderNo",orderNo);
  PhOrderInfo aux = restTemplate.getForObject(builder.toUriString(), PhOrderInfo.class)

 return aux;
}


}