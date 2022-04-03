package restock.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import restock.Interface.ProducteRepository;
public class ProducteRepositoryImpl implements ProducteRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public Producte findById(Integer id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findById"))
    .queryParam("id",id)
;  Producte aux = restTemplate.getForObject(builder.toUriString(), Producte.class);

 return aux;
}


}