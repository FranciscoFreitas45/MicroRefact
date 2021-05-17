import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class StockRepositoryImpl implements StockRepository{

 private RestTemplate restTemplate;

  String url = "http://10";


public List<Stock> findByStockType(StockType stockType){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByStockType"))
    .queryParam("stockType",stockType);
  List<Stock> aux = restTemplate.getForObject(builder.toUriString(), List<Stock>.class)

 return aux;
}


}