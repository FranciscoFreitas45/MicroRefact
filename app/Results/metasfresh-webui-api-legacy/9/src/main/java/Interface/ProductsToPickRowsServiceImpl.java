package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class ProductsToPickRowsServiceImpl implements ProductsToPickRowsService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public ProductsToPickRowsData createProductsToPickRowsData(PackageableRow packageableRow){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createProductsToPickRowsData"))
    .queryParam("packageableRow",packageableRow);
  ProductsToPickRowsData aux = restTemplate.getForObject(builder.toUriString(), ProductsToPickRowsData.class);

 return aux;
}


}