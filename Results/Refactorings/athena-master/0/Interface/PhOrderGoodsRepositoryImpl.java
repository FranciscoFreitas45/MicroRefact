import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class PhOrderGoodsRepositoryImpl implements PhOrderGoodsRepository{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public void updateStock(String orderNo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("updateStock"))
    .queryParam("orderNo",orderNo);

  restTemplate.put(builder.toUriString(), null)



}