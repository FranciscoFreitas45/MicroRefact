import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class PhBrandServiceImpl implements PhBrandService{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public List<PhBrand> findByIds(List<Long> ids){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findByIds"))
    .queryParam("ids",ids);
  List<PhBrand> aux = restTemplate.getForObject(builder.toUriString(), List<PhBrand>.class)

 return aux;
}


}