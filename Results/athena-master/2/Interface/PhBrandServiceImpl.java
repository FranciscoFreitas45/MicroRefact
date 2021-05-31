import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class PhBrandServiceImpl implements PhBrandService{

 private RestTemplate restTemplate;

  String url = "http://7";


public List<PhBrand> findByIds(List<Long> ids){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByIds"))
    .queryParam("ids",ids);
  List<PhBrand> aux = restTemplate.getForObject(builder.toUriString(), List<PhBrand>.class)

 return aux;
}


public PhBrand findOne(Long id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOne"))
    .queryParam("id",id);
  PhBrand aux = restTemplate.getForObject(builder.toUriString(), PhBrand.class)

 return aux;
}


}