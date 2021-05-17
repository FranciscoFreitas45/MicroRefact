import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class IStockServiceImpl implements IStockService{

 private RestTemplate restTemplate;

  String url = "http://10";


public List<DailyNecessaryDto> findByStockType(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByStockType"))
  List<DailyNecessaryDto> aux = restTemplate.getForObject(builder.toUriString(), List<DailyNecessaryDto>.class)

 return aux;
}


}