package restock.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import restock.Interface.ProducteBusiness;
public class ProducteBusinessImpl implements ProducteBusiness{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<Producte> getProductesPerProveidor(Integer provId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getProductesPerProveidor"))
    .queryParam("provId",provId)
;  List<Producte> aux = restTemplate.getForObject(builder.toUriString(), List<Producte>.class);

 return aux;
}


}