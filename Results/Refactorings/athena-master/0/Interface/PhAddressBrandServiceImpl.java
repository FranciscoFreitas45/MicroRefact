import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class PhAddressBrandServiceImpl implements PhAddressBrandService{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public PhAddressBrand findOne(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("findOne"))
    .queryParam("id",id);
  PhAddressBrand aux = restTemplate.getForObject(builder.toUriString(), PhAddressBrand.class)

 return aux;
}


}