package pl.szymanski.sharelibrary.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szymanski.sharelibrary.Interface.ExchangeService;
public class ExchangeServiceImpl implements ExchangeService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Exchange getExchangeById(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getExchangeById"))
    .queryParam("id",id)
;  Exchange aux = restTemplate.getForObject(builder.toUriString(), Exchange.class);

 return aux;
}


public List<Exchange> getExchangesByUserId(Long userId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getExchangesByUserId"))
    .queryParam("userId",userId)
;  List<Exchange> aux = restTemplate.getForObject(builder.toUriString(), List<Exchange>.class);

 return aux;
}


}