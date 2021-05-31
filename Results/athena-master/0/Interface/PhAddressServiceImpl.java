import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class PhAddressServiceImpl implements PhAddressService{

 private RestTemplate restTemplate;

  String url = "http://5";


public PhAddress findOne(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))
    .queryParam("id",id);
  PhAddress aux = restTemplate.getForObject(builder.toUriString(), PhAddress.class)

 return aux;
}


}