package switchtwentytwenty.project.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import switchtwentytwenty.project.Interface.MoneyValue;
public class MoneyValueImpl implements MoneyValue{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public boolean isPositiveOrZero(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isPositiveOrZero"))
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public int compare(MoneyValue moneyValue){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/compare"))
    .queryParam("moneyValue",moneyValue)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public MoneyValue subtract(MoneyValue moneyValue){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/subtract"))
    .queryParam("moneyValue",moneyValue)
;  MoneyValue aux = restTemplate.getForObject(builder.toUriString(), MoneyValue.class);

 return aux;
}


public MoneyValue sum(MoneyValue moneyValue){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/sum"))
    .queryParam("moneyValue",moneyValue)
;  MoneyValue aux = restTemplate.getForObject(builder.toUriString(), MoneyValue.class);

 return aux;
}


}