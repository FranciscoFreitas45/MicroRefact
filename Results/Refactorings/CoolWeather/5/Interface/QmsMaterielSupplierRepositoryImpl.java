import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class QmsMaterielSupplierRepositoryImpl implements QmsMaterielSupplierRepository{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public List<QmsMaterielSupplier> findBySupplierId(Integer s){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findBySupplierId"))
    .queryParam("s",s);
  List<QmsMaterielSupplier> aux = restTemplate.getForObject(builder.toUriString(), List<QmsMaterielSupplier>.class)

 return aux;
}


}