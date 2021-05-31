import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class PhBrandServiceImpl implements PhBrandService{

 private RestTemplate restTemplate;

  String url = "http://7";


public List<PhBrand> findAll(String prefix){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("prefix",prefix);
  List<PhBrand> aux = restTemplate.getForObject(builder.toUriString(), List<PhBrand>.class)

 return aux;
}


}