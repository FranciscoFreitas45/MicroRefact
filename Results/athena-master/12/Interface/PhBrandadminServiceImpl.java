import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class PhBrandadminServiceImpl implements PhBrandadminService{

 private RestTemplate restTemplate;

  String url = "http://11";


public List<Long> findBrandIdByAdminId(Long adminId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findBrandIdByAdminId"))
    .queryParam("adminId",adminId);
  List<Long> aux = restTemplate.getForObject(builder.toUriString(), List<Long>.class)

 return aux;
}


}