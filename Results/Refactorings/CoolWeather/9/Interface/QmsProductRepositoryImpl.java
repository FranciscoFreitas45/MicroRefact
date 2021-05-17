import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class QmsProductRepositoryImpl implements QmsProductRepository{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public List<QmsProduct> findByProductNumAndMaterielId(String productNum,Integer materielId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByProductNumAndMaterielId"))
    .queryParam("productNum",productNum)
    .queryParam("materielId",materielId);
  List<QmsProduct> aux = restTemplate.getForObject(builder.toUriString(), List<QmsProduct>.class)

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("save"))
    .queryParam("Object",Object);
  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class)

 return aux;
}


}