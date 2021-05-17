import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class QmsSupplierRepositoryImpl implements QmsSupplierRepository{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public Optional<QmsSupplier> findQmsSupplierBySupplierCdAndFlagStatus(String supplierCd,String flagStatus){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findQmsSupplierBySupplierCdAndFlagStatus"))
    .queryParam("supplierCd",supplierCd)
    .queryParam("flagStatus",flagStatus);
  Optional<QmsSupplier> aux = restTemplate.getForObject(builder.toUriString(), Optional<QmsSupplier>.class)

 return aux;
}


}