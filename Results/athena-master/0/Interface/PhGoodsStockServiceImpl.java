import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class PhGoodsStockServiceImpl implements PhGoodsStockService{

 private RestTemplate restTemplate;

  String url = "http://1";


public boolean updateStock(String orderNo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateStock"))
    .queryParam("orderNo",orderNo);
  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class)

 return aux;
}


}